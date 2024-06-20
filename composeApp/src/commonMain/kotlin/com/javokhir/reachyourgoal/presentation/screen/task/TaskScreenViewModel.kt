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
        _uiState.update {
            when (event) {
                is ScreenEvent.Input.TaskChanged -> onTaskChanged(it, event)
                ScreenEvent.Input.DeleteTask -> onTaskDeleted(it)
            }
        }
    }

    private fun onTaskChanged(
        state: ScreenUiState,
        event: ScreenEvent.Input.TaskChanged
    ): ScreenUiState {
        val updatedTask = state.task.copy(
            name = event.name,
            description = event.description
        )

        screenModelScope.launch(Dispatchers.IO) {
            taskRepository.update(updatedTask)
        }

        return state.copy(task = updatedTask)
    }

    private fun onTaskDeleted(state: ScreenUiState): ScreenUiState {
        screenModelScope.launch(Dispatchers.IO) {
            taskRepository.delete(state.task)
            _commands.emit(ScreenEvent.Command.Exit)
        }

        return state
    }

}