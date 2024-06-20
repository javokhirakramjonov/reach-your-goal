package presentation.bottomSheet.taskSelectorForPlan

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.model.SelectableTask
import domain.model.TaskAndPlanDto
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
import presentation.bottomSheet.taskSelectorForPlan.mvi.event.ScreenEvent
import presentation.bottomSheet.taskSelectorForPlan.mvi.state.ScreenUiState
import repository.TaskAndPlanRepository
import repository.TaskRepository

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
                    val selectedTaskIds = taskAndPlan
                        .map { it.taskId }
                        .toHashSet()

                    val selectableTasks = allTasks.map {
                        val isSelected = selectedTaskIds.contains(it.id)

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
            uiState.selectableTasks.forEach {
                if (it.isSelected) {
                    taskAndPlanRepository.insert(TaskAndPlanDto(it.task.id, uiState.planId).transform())
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