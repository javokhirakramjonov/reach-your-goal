package dialog.taskSelectorForPlan

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dao.TaskAndPlanDao
import dao.TaskDao
import dialog.taskSelectorForPlan.mvi.event.ScreenEvent
import dialog.taskSelectorForPlan.mvi.state.ScreenUiState
import domain.SelectableTask
import domain.TaskAndPlan
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskSelectorForPlanViewModel(
    private val taskDao: TaskDao,
    private val taskAndPlanDao: TaskAndPlanDao,
    uiState: ScreenUiState
) : ScreenModel {

    private val _uiState = MutableStateFlow(uiState)
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            taskAndPlanDao
                .getAllByPlanId(uiState.planId)
                .combine(taskDao.getAll()) { taskAndPlan, allTasks ->
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
            }
        }
    }

    private fun onTaskSelectionChanged(uiState: ScreenUiState, event: ScreenEvent.Input.TaskSelectionChanged): ScreenUiState {
        screenModelScope.launch(Dispatchers.IO) {
            if (event.isSelected) {
                taskAndPlanDao.insert(TaskAndPlan(event.taskId, uiState.planId, 0))
            } else {
                taskAndPlanDao.delete(uiState.planId, event.taskId)
            }
        }

        return uiState
    }

}