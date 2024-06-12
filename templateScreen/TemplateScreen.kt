package screen.template

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import screen.template.mvi.ui.ScreenUi

class TemplateScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<TemplateScreenViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        ScreenUi(uiState = uiState, action = viewModel::action)
    }
}