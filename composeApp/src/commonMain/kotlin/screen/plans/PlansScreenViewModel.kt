package screen.plans

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.entity.Plan
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import repository.PlanRepository
import screen.plans.mvi.event.ScreenEvent
import screen.plans.mvi.state.ScreenUiState
import utils.letCoroutine

class PlansScreenViewModel(
    private val planRepository: PlanRepository
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            planRepository.getAll().collect { plans ->
                _uiState.update { it.copy(plans = plans.toImmutableList()) }
            }
        }
    }

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                is ScreenEvent.Input.AddAndOpenPlan -> onPlanAdded(it, event)
                is ScreenEvent.Input.DeletePlan -> TODO()
                else -> doCommand(it, event)
            }
        }
    }

    private fun onPlanAdded(state: ScreenUiState, event: ScreenEvent.Input.AddAndOpenPlan): ScreenUiState {
        screenModelScope.launch(Dispatchers.IO) {
            val planId = planRepository.insert(Plan(name = event.name, description = event.description)).toInt()

            _commands.emit(ScreenEvent.Command.OpenPlan(Plan(id = planId, name = event.name, description = event.description)))
        }
        return state
    }

    private fun doCommand(state: ScreenUiState, event: ScreenEvent.Input): ScreenUiState {
        val command: ScreenEvent.Command? = when (event) {
            is ScreenEvent.Input.OpenPlan -> ScreenEvent.Command.OpenPlan(event.plan)
            else -> null
        }

        screenModelScope.launch {
            command?.letCoroutine(_commands::emit)
        }

        return state
    }
}
