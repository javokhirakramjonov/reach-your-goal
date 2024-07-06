package com.javokhir.reachyourgoal.presentation.screen.task

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.presentation.screen.task.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.task.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.repository.TaskRepository
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

class TaskScreenViewModel(
    private val taskRepository: TaskRepository,
    uiState: ScreenUiState
) : ScreenModel {

    private val _uiState = MutableStateFlow(uiState)
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    fun action(event: ScreenEvent.Input) {
        val newUiState: ScreenUiState? = when (event) {
            is ScreenEvent.Input.TaskChanged -> onTaskChanged(uiState.value, event)
            ScreenEvent.Input.DeleteTask -> {
                onTaskDeleted(uiState.value)
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

    private fun onTaskChanged(
        uiState: ScreenUiState,
        event: ScreenEvent.Input.TaskChanged
    ): ScreenUiState {
        val updatedTask = uiState.task.copy(
            name = event.name,
            description = event.description
        )

        screenModelScope.launch(Dispatchers.IO) {
            taskRepository.update(updatedTask)

            _commands.emit(ScreenEvent.Command.Exit)
        }

        return uiState.copy(task = updatedTask)
    }

    private fun onTaskDeleted(uiState: ScreenUiState) {
        screenModelScope.launch(Dispatchers.IO) {
            taskRepository.delete(uiState.task)
            _commands.emit(ScreenEvent.Command.Exit)
        }
    }

    private fun onExitClicked() {
        screenModelScope.launch {
            _commands.emit(ScreenEvent.Command.Exit)
        }
    }

}