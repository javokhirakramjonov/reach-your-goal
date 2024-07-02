package com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.datastore.SettingsDatastore
import com.javokhir.reachyourgoal.presentation.bottomSheet.themeSelector.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.bottomSheet.themeSelector.mvi.state.ScreenUiState
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

class ThemeSelectorViewModel(
    private val settingsDatastore: SettingsDatastore,
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            settingsDatastore
                .getCurrentTheme()
                .collect { currentTheme ->
                    _uiState.update {
                        it.copy(currentTheme = currentTheme)
                    }
                }
        }
    }

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                is ScreenEvent.Input.ThemeTypeSelected -> onThemeTypeSelected(it, event)
            }
        }
    }

    private fun onThemeTypeSelected(
        state: ScreenUiState,
        event: ScreenEvent.Input.ThemeTypeSelected
    ): ScreenUiState {
        screenModelScope.launch(Dispatchers.IO) {
            settingsDatastore.setCurrentTheme(event.themeType)
        }
        return state.copy(currentTheme = event.themeType)
    }
}