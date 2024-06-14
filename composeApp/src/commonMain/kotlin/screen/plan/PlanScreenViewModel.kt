package screen.plan

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dao.PlanDao
import dao.TaskAndPlanDao
import dao.TaskDao
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import screen.plan.mvi.event.ScreenEvent
import screen.plan.mvi.state.ScreenUiState

class PlanScreenViewModel(
    private val taskDao: TaskDao,
    private val planDao: PlanDao,
    private val taskAndPlanDao: TaskAndPlanDao,
    uiState: ScreenUiState
) : ScreenModel {

    private val _uiState = MutableStateFlow(uiState)
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands : SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            launch {
                taskAndPlanDao
                    .getAllByPlanId(uiState.plan.id)
                    .map { taskAndPlans ->
                        taskAndPlans.map { taskDao.getById(it.taskId) }
                    }
                    .collect { tasks ->
                        _uiState.update { it.copy(tasks = tasks.toImmutableList()) }
                    }
            }
        }
    }

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                ScreenEvent.Input.UpdateTasksClicked -> onUpdateTasksClicked(it)
            }
        }
    }

    private fun onUpdateTasksClicked(uiState: ScreenUiState): ScreenUiState {
        screenModelScope.launch {
            _commands.emit(ScreenEvent.Command.ShowTaskSelector)
        }
        return uiState
    }

}