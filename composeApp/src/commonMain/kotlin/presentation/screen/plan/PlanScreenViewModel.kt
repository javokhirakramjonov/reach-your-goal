package presentation.screen.plan

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
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
import repository.PlanRepository
import repository.TaskAndPlanRepository
import repository.TaskRepository
import presentation.screen.plan.domain.ScreenError
import presentation.screen.plan.mvi.event.ScreenEvent
import presentation.screen.plan.mvi.state.ScreenUiState

class PlanScreenViewModel(
    private val taskRepository: TaskRepository,
    private val planRepository: PlanRepository,
    private val taskAndPlanRepository: TaskAndPlanRepository,
    uiState: ScreenUiState
) : ScreenModel {

    private val _uiState = MutableStateFlow(uiState)
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands : SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            launch {
                taskAndPlanRepository
                    .getAllByPlanId(uiState.plan.id)
                    .map { taskAndPlans ->
                        taskAndPlans.map { taskRepository.getById(it.taskId) }
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
                ScreenEvent.Input.DeletePlan -> onPlanDeleteClicked(it)
                is ScreenEvent.Input.PlanChanged -> onPlanChanged(it, event)
            }
        }
    }

    private fun onUpdateTasksClicked(uiState: ScreenUiState): ScreenUiState {
        screenModelScope.launch {
            if(taskRepository.count() == 0) {
                _commands.emit(ScreenEvent.Command.ShowErrorSnackbar(ScreenError.NO_TASKS_AVAILABLE))
            } else {
                _commands.emit(ScreenEvent.Command.ShowTaskSelector)
            }
        }
        return uiState
    }

    private fun onPlanDeleteClicked(uiState: ScreenUiState): ScreenUiState {
        screenModelScope.launch {
            planRepository.delete(uiState.plan)
            _commands.emit(ScreenEvent.Command.Exit)
        }
        return uiState
    }

    private fun onPlanChanged(uiState: ScreenUiState, event: ScreenEvent.Input.PlanChanged): ScreenUiState {
        val updatedPlan = uiState.plan.copy(
            name = event.name,
            description = event.description
        )

        screenModelScope.launch(Dispatchers.IO) {
            planRepository.update(updatedPlan)
        }

        return uiState.copy(plan = updatedPlan)
    }

}