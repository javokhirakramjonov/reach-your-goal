package presentation.bottomSheet.taskSelectorForPlan.mvi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import presentation.bottomSheet.taskSelectorForPlan.mvi.event.ScreenEvent
import presentation.bottomSheet.taskSelectorForPlan.mvi.state.ScreenUiState
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.plan_screen_tasks
import reach_your_goal.composeapp.generated.resources.save

@Composable
fun ScreenUi(
    modifier: Modifier = Modifier,
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit
) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(Res.string.plan_screen_tasks),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            SelectableTasks(
                modifier = Modifier.weight(1f),
                selectableTasks = uiState.selectableTasks
            ) { taskId, isSelected ->
                action(ScreenEvent.Input.TaskSelectionChanged(taskId, isSelected))
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = { action(ScreenEvent.Input.SaveClicked) }
            ) {
                Text(text = stringResource(Res.string.save))
            }
        }
    }
}