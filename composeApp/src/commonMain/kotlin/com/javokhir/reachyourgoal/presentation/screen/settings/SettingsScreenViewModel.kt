package com.javokhir.reachyourgoal.presentation.screen.settings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.presentation.screen.settings.domain.SettingsItem
import com.javokhir.reachyourgoal.presentation.screen.settings.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.settings.mvi.state.ScreenUiState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsScreenViewModel : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        loadSettingsItems()
    }

    private fun loadSettingsItems() {
        _uiState.update {
            it.copy(
                settingsItems = listOf(

                    SettingsItem.Clickable.SelectTheme {
                        screenModelScope.launch {
                            _commands.emit(ScreenEvent.Command.OpenThemeSettings)
                        }
                    },
                    SettingsItem.Clickable.SelectLanguage {
                        screenModelScope.launch {
                            _commands.emit(ScreenEvent.Command.OpenLanguageSettings)
                        }
                    }
                ).toImmutableList()
            )
        }
    }

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                else -> it
            }
        }
    }

}