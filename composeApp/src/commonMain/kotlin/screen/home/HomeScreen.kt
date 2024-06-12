package screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.collectLatest
import screen.home.mvi.event.ScreenEvent
import screen.home.mvi.ui.ScreenUi
import screen.task.TaskScreen

class HomeScreen() : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel = koinScreenModel<HomeScreenViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.commands.collectLatest { command ->
                when (command) {
                    is ScreenEvent.Command.OpenTask -> navigator.push(TaskScreen(command.task))
                }
            }
        }

        ScreenUi(
            uiState = uiState,
            action = viewModel::action
        )
    }

}