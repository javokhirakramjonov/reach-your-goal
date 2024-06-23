package screen.template.mvi.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import screen.template.mvi.event.ScreenEvent
import screen.template.mvi.state.ScreenUiState

@Composable
fun ScreenUi(
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) {

    }
}