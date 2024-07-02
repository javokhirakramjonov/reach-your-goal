package com.javokhir.reachyourgoal.presentation.screen.week

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.presentation.screen.week.domain.ScreenError
import com.javokhir.reachyourgoal.presentation.screen.week.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.week.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.repository.TaskAndWeekRepository
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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeekScreenViewModel(
    private val taskRepository: TaskRepository,
    private val taskAndWeekRepository: TaskAndWeekRepository,
    uiState: ScreenUiState
) : ScreenModel {

    private val _uiState = MutableStateFlow(uiState)
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            launch {
                taskAndWeekRepository
                    .getAllByWeekId(uiState.week.id)
                    .map { taskAndWeeks ->
                        taskAndWeeks.map { taskRepository.getById(it.taskId) }
                    }
                    .collect { tasks ->
                        _uiState.update { it.copy(tasks = tasks.toImmutableList()) }
                    }
            }
        }
    }

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                ScreenEvent.Input.UpdateTasksClicked -> onUpdateTasksClicked(it)
                ScreenEvent.Input.Exit -> onExitClicked(it)
            }
        }
    }

    private fun onUpdateTasksClicked(uiState: ScreenUiState): ScreenUiState {
        screenModelScope.launch {
            if (taskRepository.count() == 0) {
                _commands.emit(ScreenEvent.Command.ShowErrorSnackbar(ScreenError.NO_TASKS_AVAILABLE))
            } else {
                _commands.emit(ScreenEvent.Command.ShowTaskSelector)
            }
        }
        return uiState
    }

    private fun onExitClicked(state: ScreenUiState): ScreenUiState {
        screenModelScope.launch {
            _commands.emit(ScreenEvent.Command.Exit)
        }
        return state
    }

}