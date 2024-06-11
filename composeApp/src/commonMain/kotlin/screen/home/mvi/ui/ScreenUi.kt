package screen.home.mvi.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import screen.home.mvi.event.ScreenEvent
import screen.home.mvi.state.ScreenUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenUi(
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit,
) {
    var number by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            TaskList(
                modifier = Modifier.fillMaxSize(),
                tasks = uiState.tasks
            ) {
                TODO()
            }
        },
        floatingActionButton = {
            AddTaskButton {
                action(ScreenEvent.Input.AddTask(number.toString()))
                number ++
            }
        }
    )
}