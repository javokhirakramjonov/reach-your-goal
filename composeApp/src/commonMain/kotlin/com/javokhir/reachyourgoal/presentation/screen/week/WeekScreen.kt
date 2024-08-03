package com.javokhir.reachyourgoal.presentation.screen.week

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.javokhir.reachyourgoal.domain.entity.Week
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek.TaskSelectorForWeek
import com.javokhir.reachyourgoal.presentation.screen.week.domain.ScreenError
import com.javokhir.reachyourgoal.presentation.screen.week.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.week.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.presentation.screen.week.mvi.ui.ScreenUi
import com.javokhir.reachyourgoal.theme.MainAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf

class WeekScreen(
    private val week: Week
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        val coroutineScope = rememberCoroutineScope()

        val viewModel =
            koinScreenModel<WeekScreenViewModel>(parameters = { parametersOf(ScreenUiState(week)) })
        val uiState by viewModel.uiState.collectAsState()

        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(Unit) {
            viewModel.commands.collectLatest { command ->
                when (command) {
                    ScreenEvent.Command.ShowTaskSelector -> coroutineScope.launch {
                        bottomSheetNavigator.show(TaskSelectorForWeek(week.id))
                    }

                    ScreenEvent.Command.Exit -> navigator.pop()

                    is ScreenEvent.Command.ShowErrorSnackbar -> showErrorSnackbar(
                        coroutineScope,
                        snackbarHostState,
                        command.error
                    )
                }
            }
        }

        MainAppTheme {
            ScreenUi(
                snackbarHostState = snackbarHostState,
                uiState = uiState,
                action = viewModel::action
            )
        }
    }
}

private fun showErrorSnackbar(
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    error: ScreenError
) {
    coroutineScope.launch {
        val errorMessage =
            when (error) {
                is ScreenError.NoTasksAvailable -> error.text
            }

        snackbarHostState.showSnackbar(errorMessage)
    }
}