package com.javokhir.reachyourgoal.presentation.screen.tasks

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.domain.entity.Task
import com.javokhir.reachyourgoal.presentation.screen.tasks.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.tasks.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.repository.TaskRepository
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

class TasksScreenViewModel(
    private val taskRepository: TaskRepository
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            taskRepository.getAll().collect { tasks ->
                _uiState.update { it.copy(tasks = tasks.toImmutableList()) }
            }
        }
    }

    fun action(event: ScreenEvent.Input) {
        val newUiState: ScreenUiState? = when (event) {
            is ScreenEvent.Input.AddTask -> {
                onTaskAdded(event)
                null
            }

            is ScreenEvent.Input.DeleteTaskClicked -> {
                onDeleteTaskClicked(event)
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

    private fun onTaskAdded(event: ScreenEvent.Input.AddTask) {
        screenModelScope.launch(Dispatchers.IO) {
            taskRepository.insert(Task(name = event.name, description = event.description))
        }
    }

    private fun onDeleteTaskClicked(
        event: ScreenEvent.Input.DeleteTaskClicked
    ) {
        screenModelScope.launch(Dispatchers.IO) {
            taskRepository.delete(event.task)
        }
    }

    private fun doCommand(event: ScreenEvent.Input) {
        val command: ScreenEvent.Command? = when (event) {
            is ScreenEvent.Input.OpenTask -> ScreenEvent.Command.OpenTask(event.task)
            else -> null
        }

        screenModelScope.launch {
            command?.letCoroutine(_commands::emit)
        }
    }

}