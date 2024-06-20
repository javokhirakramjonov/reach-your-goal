package presentation.screen.plan.mvi.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import presentation.screen.plan.mvi.event.ScreenEvent
import presentation.screen.plan.mvi.state.ScreenUiState
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.plan_screen_plan_description
import reach_your_goal.composeapp.generated.resources.plan_screen_plan_name
import reach_your_goal.composeapp.generated.resources.plan_screen_tasks
import reach_your_goal.composeapp.generated.resources.plan_screen_update_plan
import reach_your_goal.composeapp.generated.resources.plan_screen_update_tasks

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
        Column(
            modifier = Modifier
                .padding(it)
                .padding(8.dp),
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = stringResource(Res.string.plan_screen_plan_name)) },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = stringResource(Res.string.plan_screen_plan_description)) },
                modifier = Modifier.fillMaxWidth()
            )

            Text(text = stringResource(Res.string.plan_screen_tasks))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(uiState.tasks, key = { it.id }) { task ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.primary)
                            .padding(8.dp),
                    ) {
                        Text(text = task.name)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { action(ScreenEvent.Input.UpdateTasksClicked) }
            ) {
                Text(text = stringResource(Res.string.plan_screen_update_tasks))
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { action(ScreenEvent.Input.PlanChanged(name, description)) }
            ) {
                Text(text = stringResource(Res.string.plan_screen_update_plan))
            }

            IconButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { action(ScreenEvent.Input.DeletePlan) }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}