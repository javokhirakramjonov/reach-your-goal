package com.javokhir.reachyourgoal.presentation.screen.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.javokhir.reachyourgoal.presentation.screen.main.mvi.ui.ScreenUi
import com.javokhir.reachyourgoal.theme.MainAppTheme

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<MainScreenViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        MainAppTheme {
            ScreenUi(uiState = uiState, action = viewModel::action)
        }
    }
}