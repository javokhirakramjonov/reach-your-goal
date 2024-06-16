package screen.plan.mvi.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import screen.plan.mvi.event.ScreenEvent
import screen.plan.mvi.state.ScreenUiState

@Composable
fun ScreenUi(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit
) {
    var name by remember(uiState.plan.name) { mutableStateOf(uiState.plan.name) }
    var description by remember(uiState.plan.description) { mutableStateOf(uiState.plan.description.orEmpty()) }

    Scaffold(
        modifier = modifier,
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 40.dp),
                    text = "PlanScreen",
                    style = MaterialTheme.typography.headlineMedium
                )

                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { action(ScreenEvent.Input.DeletePlan) }
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete plan")
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(
                    snackbarData = it,
                    contentColor = MaterialTheme.colorScheme.error,
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = { action(ScreenEvent.Input.UpdateTasksClicked) }) {
                Text(text = "Update tasks")
            }

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(uiState.tasks, key = { it.id }) { task ->
                    Text(text = task.name)
                }
            }

            Button(onClick = { action(ScreenEvent.Input.PlanChanged(name, description)) }) {
                Text(text = "Update plan")
            }
        }
    }
}