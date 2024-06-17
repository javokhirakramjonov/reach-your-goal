package screen.task

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.entity.Task
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.parameter.parametersOf
import screen.task.mvi.event.ScreenEvent
import screen.task.mvi.state.ScreenUiState
import screen.task.mvi.ui.ScreenUi

class TaskScreen(
    val task: Task
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel =
            koinScreenModel<TaskScreenViewModel>(parameters = { parametersOf(ScreenUiState(task)) })
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.commands.collectLatest {
                when (it) {
                    ScreenEvent.Command.Exit -> navigator.pop()
                }
            }
        }

        ScreenUi(uiState = uiState, action = viewModel::action)
    }
}