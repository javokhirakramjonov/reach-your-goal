package screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import screen.home.mvi.ui.ScreenUi

class HomeScreen() : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<HomeScreenViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        ScreenUi(
            uiState = uiState,
            action = viewModel::action
        )
    }

}