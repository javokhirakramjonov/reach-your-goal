package com.javokhir.reachyourgoal.presentation.screen.statistics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.ui.ScreenUi

class StatisticsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<StatisticsScreenViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        ScreenUi(uiState = uiState)
    }
}