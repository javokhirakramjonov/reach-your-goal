package com.javokhir.reachyourgoal.presentation.screen.tasks.mvi.ui

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
import com.javokhir.reachyourgoal.AppLocale
import com.javokhir.reachyourgoal.presentation.dialog.addTask.TaskCreatorDialog
import com.javokhir.reachyourgoal.presentation.screen.tasks.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.tasks.mvi.state.ScreenUiState

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
            if (uiState.tasks.isEmpty()) {
                Text(AppLocale.current.errorMessages.noTasksAvailable)
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

    if (showTaskCreatorDialog) {
        TaskCreatorDialog(
            onDismissRequest = { showTaskCreatorDialog = false },
            onCreateClicked = { name, description ->
                action(ScreenEvent.Input.AddTask(name, description))
            }
        )
    }
}