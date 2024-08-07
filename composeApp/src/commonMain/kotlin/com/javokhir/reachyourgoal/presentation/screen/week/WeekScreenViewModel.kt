package com.javokhir.reachyourgoal.presentation.screen.week

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.datastore.SettingsDatastore
import com.javokhir.reachyourgoal.presentation.screen.week.domain.ScreenError
import com.javokhir.reachyourgoal.presentation.screen.week.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.week.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.repository.TaskRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeekScreenViewModel(
    private val taskRepository: TaskRepository,
    private val settingsDatastore: SettingsDatastore,
    uiState: ScreenUiState
) : ScreenModel {

    private val _uiState = MutableStateFlow(uiState)
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            launch {
                taskRepository
                    .getAllByWeekId(uiState.week.id)
                    .collect { tasks ->
                        _uiState.update { it.copy(tasks = tasks.toImmutableList()) }
                    }
            }
        }
    }

    fun action(event: ScreenEvent.Input) {
        val newUiState: ScreenUiState? = when (event) {
            ScreenEvent.Input.UpdateTasksClicked -> {
                onUpdateTasksClicked()
                null
            }

            ScreenEvent.Input.Exit -> {
                onExitClicked()
                null
            }
        }

        if (newUiState != null) {
            _uiState.update { newUiState }
        }
    }

    private fun onUpdateTasksClicked() {
        screenModelScope.launch {
            if (taskRepository.count() == 0) {
                val errorMessage = settingsDatastore
                    .getAppLocale()
                    .last()
                    .errorMessages
                    .noTasksAvailable

                _commands.emit(
                    ScreenEvent.Command.ShowErrorSnackbar(
                        ScreenError.NoTasksAvailable(
                            errorMessage
                        )
                    )
                )
            } else {
                _commands.emit(ScreenEvent.Command.ShowTaskSelector)
            }
        }
    }

    private fun onExitClicked() {
        screenModelScope.launch {
            _commands.emit(ScreenEvent.Command.Exit)
        }
    }

}