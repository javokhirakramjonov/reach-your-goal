package com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.domain.entity.TaskAndWeek
import com.javokhir.reachyourgoal.domain.entity.TaskState
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import com.javokhir.reachyourgoal.domain.model.SelectableTask
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.repository.TaskAndWeekRepository
import com.javokhir.reachyourgoal.repository.TaskRepository
import com.javokhir.reachyourgoal.repository.TaskStateRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.DayOfWeek

class TaskSelectorForWeekViewModel(
    private val taskRepository: TaskRepository,
    private val taskAndWeekRepository: TaskAndWeekRepository,
    private val taskStateRepository: TaskStateRepository,
    uiState: ScreenUiState
) : ScreenModel {

    private val _uiState = MutableStateFlow(uiState)
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    private var initialSelectedTaskIds: Set<Int> = emptySet()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            taskAndWeekRepository
                .getAllByWeekId(uiState.weekId)
                .combine(taskRepository.getAll()) { taskAndWeek, allTasks ->
                    val selectedTasks = taskAndWeek.associateBy { it.taskId }
                    if (initialSelectedTaskIds.isEmpty()) {
                        initialSelectedTaskIds = selectedTasks.keys
                    }

                    val selectableTasks = allTasks.map {
                        val isSelected = it.id in selectedTasks

                        SelectableTask(it, isSelected)
                    }

                    selectableTasks
                }
                .collect { selectableTasks ->
                    _uiState.update { it.copy(selectableTasks = selectableTasks.toImmutableList()) }
                }
        }
    }

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                is ScreenEvent.Input.TaskSelectionChanged -> onTaskSelectionChanged(it, event)
                ScreenEvent.Input.SaveClicked -> onSaveClicked(it)
            }
        }
    }

    private fun onTaskSelectionChanged(
        uiState: ScreenUiState,
        event: ScreenEvent.Input.TaskSelectionChanged
    ): ScreenUiState {
        return uiState
            .copy(
                selectableTasks = uiState.selectableTasks
                    .map {
                        if (it.task.id == event.taskId) it.copy(isSelected = event.isSelected)
                        else it
                    }
                    .toImmutableList()
            )
    }

    private fun onSaveClicked(uiState: ScreenUiState): ScreenUiState {
        screenModelScope.launch(Dispatchers.IO) {
            uiState.selectableTasks.forEach { selectableTask ->
                if (selectableTask.isSelected) {
                    if (selectableTask.task.id !in initialSelectedTaskIds) {
                        DayOfWeek
                            .entries
                            .map {
                                TaskState(
                                    taskId = selectableTask.task.id,
                                    weekId = uiState.weekId,
                                    day = it,
                                    status = TaskStatus.NOT_STARTED
                                )
                            }.forEach {
                                taskStateRepository.insert(it)
                            }
                    }

                    taskAndWeekRepository.insert(
                        TaskAndWeek(
                            selectableTask.task.id,
                            uiState.weekId,
                        )
                    )
                } else {
                    taskStateRepository.deleteAllByTaskIdAndWeekId(
                        selectableTask.task.id,
                        uiState.weekId
                    )
                    taskAndWeekRepository.delete(uiState.weekId, selectableTask.task.id)
                }
            }
        }
        screenModelScope.launch {
            _commands.emit(ScreenEvent.Command.Exit)
        }
        return uiState
    }

}