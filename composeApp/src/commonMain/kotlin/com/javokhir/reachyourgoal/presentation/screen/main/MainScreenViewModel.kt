package com.javokhir.reachyourgoal.presentation.screen.main

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.domain.entity.TaskAndWeek
import com.javokhir.reachyourgoal.presentation.screen.main.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.main.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.repository.TaskAndWeekRepository
import com.javokhir.reachyourgoal.repository.TaskRepository
import com.javokhir.reachyourgoal.repository.WeekRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val weekRepository: WeekRepository,
    private val taskRepository: TaskRepository,
    private val taskAndWeekRepository: TaskAndWeekRepository,
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

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

    private suspend fun loadCurrentWeek(weekId: Int) {
        weekRepository
            .getById(weekId)
            .collect { week ->
                _uiState.update { it.copy(currentWeek = week) }
            }
    }

    private suspend fun loadTasksForWeek(weekId: Int) {
        taskAndWeekRepository
            .getAllByWeekId(weekId)
            .collect { tasks ->
                val scheduledTasks = tasks
                    .map {
                        val task = taskRepository.getById(it.taskId)

                        task to it.statuses.toImmutableList()
                    }
                    .toImmutableList()

                _uiState.update { it.copy(scheduledTasks = scheduledTasks) }
            }
    }

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                is ScreenEvent.Input.WeekSelected -> {
                    //TODO
                    it
                }

                is ScreenEvent.Input.StatusChanged -> onStatusChanged(it, event)
            }
        }
    }

    private fun onStatusChanged(
        state: ScreenUiState,
        event: ScreenEvent.Input.StatusChanged
    ): ScreenUiState {
        screenModelScope.launch(Dispatchers.IO) {
            taskAndWeekRepository.update(
                TaskAndWeek(
                    weekId = state.currentWeek!!.id,
                    taskId = event.taskId,
                    statuses = state
                        .scheduledTasks
                        .first { it.first.id == event.taskId }
                        .second
                        .mapIndexed { index, status ->
                            if (index == event.day) {
                                event.status
                            } else {
                                status
                            }
                        }
                )
            )
        }

        return state.copy(
            scheduledTasks = state.scheduledTasks.map {
                if (it.first.id == event.taskId) {
                    it.first to it.second.mapIndexed { index, status ->
                        if (index == event.day) {
                            event.status
                        } else {
                            status
                        }
                    }.toImmutableList()
                } else {
                    it
                }
            }.toImmutableList()
        )
    }

}