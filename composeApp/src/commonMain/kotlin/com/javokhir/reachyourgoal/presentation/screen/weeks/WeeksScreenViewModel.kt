package com.javokhir.reachyourgoal.presentation.screen.weeks

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.presentation.screen.weeks.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.weeks.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.repository.WeekRepository
import com.javokhir.reachyourgoal.utils.letCoroutine
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

class WeeksScreenViewModel(
    private val weekRepository: WeekRepository
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            weekRepository.getAll().collect { weeks ->
                _uiState.update { it.copy(weeks = weeks.toImmutableList()) }
            }
        }
    }

    fun action(event: ScreenEvent.Input) {
        val newUiState: ScreenUiState? = when (event) {
            ScreenEvent.Input.CreateNextWeek -> {
                onCreateNextWeek()
                null
            }

            else -> {
                doCommand(event)
                null
            }
        }

        if (newUiState != null) {
            _uiState.update { newUiState }
        }
    }

    private fun onCreateNextWeek() {
        screenModelScope.launch(Dispatchers.IO) {
            weekRepository.createNewWeek()
        }
    }

    private fun doCommand(event: ScreenEvent.Input) {
        val command: ScreenEvent.Command? = when (event) {
            is ScreenEvent.Input.OpenWeek -> ScreenEvent.Command.OpenWeek(event.week)
            else -> null
        }

        screenModelScope.launch {
            command?.letCoroutine(_commands::emit)
        }
    }
}
