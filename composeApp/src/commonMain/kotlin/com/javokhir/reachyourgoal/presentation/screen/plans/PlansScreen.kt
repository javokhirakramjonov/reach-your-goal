package com.javokhir.reachyourgoal.presentation.screen.plans

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.javokhir.reachyourgoal.presentation.screen.plan.PlanScreen
import com.javokhir.reachyourgoal.presentation.screen.plans.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.plans.mvi.ui.ScreenUi
import com.javokhir.reachyourgoal.theme.MainAppTheme
import kotlinx.coroutines.flow.collectLatest

class PlansScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel = koinScreenModel<PlansScreenViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.commands.collectLatest { command ->
                when (command) {
                    is ScreenEvent.Command.OpenPlan -> navigator.push(PlanScreen(command.plan))
                }
            }
        }

        MainAppTheme {
            ScreenUi(uiState = uiState, action = viewModel::action)
        }
    }
}