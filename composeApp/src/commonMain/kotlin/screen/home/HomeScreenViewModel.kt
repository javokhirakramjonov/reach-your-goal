package screen.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dao.TaskDao
import domain.Task
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import screen.home.mvi.event.ScreenEvent
import screen.home.mvi.state.ScreenUiState

class HomeScreenViewModel(
    private val dao: TaskDao
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            dao.getAll().collect { tasks ->
                _uiState.update { it.copy(tasks = tasks.toImmutableList()) }
            }
        }
    }

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                is ScreenEvent.Input.AddTask -> onTaskAdded(it, event)
                is ScreenEvent.Input.DeleteTask -> TODO()
            }
        }
    }

    private fun onTaskAdded(state: ScreenUiState, event: ScreenEvent.Input.AddTask): ScreenUiState {
        screenModelScope.launch(Dispatchers.IO) {
            dao.upsert(Task(name = event.name))
        }
        return state
    }

}