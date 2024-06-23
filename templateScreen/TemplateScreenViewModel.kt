package screen.template

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import screen.template.mvi.event.ScreenEvent
import screen.template.mvi.state.ScreenUiState

class TemplateScreenViewModel : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState : StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                else -> it
            }
        }
    }

}