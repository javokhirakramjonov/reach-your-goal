package screen.tasks

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dao.TaskDao
import domain.Task
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
import screen.tasks.mvi.event.ScreenEvent
import screen.tasks.mvi.state.ScreenUiState
import utils.letCoroutine

class TasksScreenViewModel(
    private val taskDao: TaskDao
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            taskDao.getAll().collect { tasks ->
                _uiState.update { it.copy(tasks = tasks.toImmutableList()) }
            }
        }
    }

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                is ScreenEvent.Input.AddTask -> onTaskAdded(it, event)
                is ScreenEvent.Input.DeleteTask -> TODO()
                else -> doCommand(it, event)
            }
        }
    }

    private fun onTaskAdded(state: ScreenUiState, event: ScreenEvent.Input.AddTask): ScreenUiState {
        screenModelScope.launch(Dispatchers.IO) {
            taskDao.insert(Task(name = event.name, description = event.description))
        }
        return state
    }

    private fun doCommand(state: ScreenUiState, event: ScreenEvent.Input) : ScreenUiState {
        val command : ScreenEvent.Command? = when (event) {
            is ScreenEvent.Input.OpenTask -> ScreenEvent.Command.OpenTask(event.task)
            else -> null
        }

        screenModelScope.launch {
            command?.letCoroutine(_commands::emit)
        }

        return state
    }

}