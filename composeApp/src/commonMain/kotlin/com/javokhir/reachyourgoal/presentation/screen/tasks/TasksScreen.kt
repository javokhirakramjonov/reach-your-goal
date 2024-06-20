package com.javokhir.reachyourgoal.presentation.screen.tasks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.javokhir.reachyourgoal.presentation.screen.task.TaskScreen
import com.javokhir.reachyourgoal.presentation.screen.tasks.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.tasks.mvi.ui.ScreenUi
import kotlinx.coroutines.flow.collectLatest

class TasksScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel = koinScreenModel<TasksScreenViewModel>()
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