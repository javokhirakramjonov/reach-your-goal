package com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForPlan

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.domain.entity.TaskAndPlan
import com.javokhir.reachyourgoal.domain.model.SelectableTask
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForPlan.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForPlan.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.repository.TaskAndPlanRepository
import com.javokhir.reachyourgoal.repository.TaskRepository
import com.javokhir.reachyourgoal.utils.weeklyStatusTemplate
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

class TaskSelectorForPlanViewModel(
    private val taskRepository: TaskRepository,
    private val taskAndPlanRepository: TaskAndPlanRepository,
    uiState: ScreenUiState
) : ScreenModel {

    private val _uiState = MutableStateFlow(uiState)
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            taskAndPlanRepository
                .getAllByPlanId(uiState.planId)
                .combine(taskRepository.getAll()) { taskAndPlan, allTasks ->
                    val selectedTasks = taskAndPlan.associateBy { it.taskId }

                    val selectableTasks = allTasks.map {
                        val isSelected = it.id in selectedTasks

                        val statuses = selectedTasks[it.id]?.statuses ?: weeklyStatusTemplate()

                        SelectableTask(it, isSelected, statuses)
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
            uiState.selectableTasks.forEach {
                if (it.isSelected) {
                    taskAndPlanRepository.insert(
                        TaskAndPlan(
                            it.task.id,
                            uiState.planId,
                            it.statuses
                        )
                    )
                } else {
                    taskAndPlanRepository.delete(uiState.planId, it.task.id)
                }
            }
        }
        screenModelScope.launch {
            _commands.emit(ScreenEvent.Command.Exit)
        }
        return uiState
    }

}