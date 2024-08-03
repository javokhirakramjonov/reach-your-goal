package com.javokhir.reachyourgoal.presentation.bottomSheet.languageSelector

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.datastore.SettingsDatastore
import com.javokhir.reachyourgoal.presentation.bottomSheet.languageSelector.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.bottomSheet.languageSelector.mvi.state.ScreenUiState
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

class LanguageSelectorViewModel(
    private val settingsDatastore: SettingsDatastore,
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            settingsDatastore
                .getLanguage()
                .collect { currentLanguage ->
                    _uiState.update {
                        it.copy(currentLanguage = currentLanguage)
                    }
                }
        }
    }

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                is ScreenEvent.Input.LanguageSelected -> onLanguageSelected(it, event)
            }
        }
    }

    private fun onLanguageSelected(
        uiState: ScreenUiState,
        event: ScreenEvent.Input.LanguageSelected
    ): ScreenUiState {
        screenModelScope.launch(Dispatchers.IO) {
            settingsDatastore.setLanguage(event.language)
        }
        return uiState.copy(currentLanguage = event.language)
    }
}