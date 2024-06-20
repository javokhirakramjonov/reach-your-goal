package presentation.screen.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import presentation.screen.main.mvi.ui.ScreenUi

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<MainScreenViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        ScreenUi(uiState = uiState, action = viewModel::action)
    }
}