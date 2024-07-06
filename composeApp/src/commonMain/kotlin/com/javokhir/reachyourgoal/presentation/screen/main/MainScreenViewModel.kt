package com.javokhir.reachyourgoal.presentation.screen.main

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.presentation.screen.main.model.TaskAndStates
import com.javokhir.reachyourgoal.presentation.screen.main.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.main.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.repository.TaskRepository
import com.javokhir.reachyourgoal.repository.TaskStateRepository
import com.javokhir.reachyourgoal.repository.WeekRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.isoDayNumber

class MainScreenViewModel(
    private val weekRepository: WeekRepository,
    private val taskRepository: TaskRepository,
    private val taskStateRepository: TaskStateRepository,
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private var weekLoader: Job? = null

    init {
        screenModelScope.launch(Dispatchers.IO) {
            launch { loadWeeks() }
        }
    }

    private suspend fun loadWeeks() {
        weekRepository
            .getAll()
            .collect { weeks ->
                _uiState.update { it.copy(weeks = weeks.toImmutableList()) }
            }
    }

    private fun loadWeekById(weekId: Int) {
        if (weekId == uiState.value.currentWeek?.id) return

        _uiState.update {
            it.copy(
                currentWeek = null,
                scheduledTasks = emptyList<TaskAndStates>().toImmutableList()
            )
        }

        weekLoader?.cancel()

        weekLoader = screenModelScope.launch(Dispatchers.IO) {

            val week = weekRepository.getById(weekId)

            _uiState.update {
                it.copy(
                    currentWeek = week
                )
            }

            loadTasksForWeek(weekId)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun loadTasksForWeek(weekId: Int) {
        taskRepository
            .getAllByWeekId(weekId)
            .flatMapLatest {
                combine(
                    it.map { task ->
                        taskStateRepository
                            .getAllByTaskIdAndWeekId(task.id, weekId)
                            .map {
                                TaskAndStates(
                                    task = task,
                                    states = it
                                        .sortedBy { it.day.isoDayNumber }
                                        .toImmutableList()
                                )
                            }
                    }
                ) {
                    it.toList().toImmutableList()
                }
            }.collect { scheduledTasks ->
                _uiState.update {
                    it.copy(scheduledTasks = scheduledTasks)
                }
            }
    }

    fun action(event: ScreenEvent.Input) {
        val newState: ScreenUiState? = when (event) {
            is ScreenEvent.Input.WeekSelected -> {
                onWeekSelected(event)
                null
            }

            is ScreenEvent.Input.StatusChanged -> {
                onStatusChanged(event)
                null
            }
        }


        if (newState != null) {
            _uiState.update { newState }
        }
    }

    private fun onWeekSelected(
        event: ScreenEvent.Input.WeekSelected
    ) {
        loadWeekById(event.week.id)
    }

    private fun onStatusChanged(
        event: ScreenEvent.Input.StatusChanged
    ) {
        screenModelScope.launch(Dispatchers.IO) {
            taskStateRepository.update(event.taskState)
        }
    }

}