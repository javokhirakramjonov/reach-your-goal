package presentation.screen.tasks.mvi.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import presentation.dialog.addTask.TaskCreatorDialog
import presentation.screen.tasks.mvi.event.ScreenEvent
import presentation.screen.tasks.mvi.state.ScreenUiState
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.no_tasks

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            if(uiState.tasks.isEmpty()) {
                Text(stringResource(Res.string.no_tasks))
            } else {
                TaskList(
                    modifier = Modifier.fillMaxSize(),
                    columns = 1,
                    tasks = uiState.tasks,
                    onTaskClick = { action(ScreenEvent.Input.OpenTask(it)) },
                    onDeleteClick = { action(ScreenEvent.Input.DeleteTaskClicked(it)) }
                )
            }
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