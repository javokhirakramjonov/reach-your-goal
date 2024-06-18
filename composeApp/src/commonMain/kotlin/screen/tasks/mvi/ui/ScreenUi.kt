package screen.tasks.mvi.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dialog.addTask.TaskCreatorDialog
import screen.tasks.mvi.event.ScreenEvent
import screen.tasks.mvi.state.ScreenUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenUi(
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit,
) {
    var showTaskCreatorDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            AddTaskButton {
                showTaskCreatorDialog = true
            }
        }
    ) {
        TaskList(
            modifier = Modifier.padding(it),
            columns = 1,
            tasks = uiState.tasks
        ) {
            action(ScreenEvent.Input.OpenTask(it))
        }
    }

    if(showTaskCreatorDialog) {
        TaskCreatorDialog(
            onDismissRequest = { showTaskCreatorDialog = false },
            onCreateClicked = { name, description ->
                action(ScreenEvent.Input.AddTask(name, description))
            }
        )
    }
}