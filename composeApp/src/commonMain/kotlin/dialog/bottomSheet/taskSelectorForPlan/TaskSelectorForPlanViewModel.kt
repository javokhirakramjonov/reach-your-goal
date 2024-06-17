package dialog.bottomSheet.taskSelectorForPlan

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dao.TaskAndPlanDao
import dao.TaskDao
import dialog.bottomSheet.taskSelectorForPlan.mvi.event.ScreenEvent
import dialog.bottomSheet.taskSelectorForPlan.mvi.state.ScreenUiState
import domain.model.SelectableTask
import domain.entity.TaskAndPlan
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
                ScreenEvent.Input.TasksChanged -> onTasksChanged(it)
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

    private fun onTasksChanged(uiState: ScreenUiState): ScreenUiState {
        screenModelScope.launch(Dispatchers.IO) {
            uiState.selectableTasks.forEach {
                if (it.isSelected) {
                    taskAndPlanDao.insert(TaskAndPlan(it.task.id, uiState.planId, 0))
                } else {
                    taskAndPlanDao.delete(uiState.planId, it.task.id)
                }
            }
        }
        return uiState
    }

}