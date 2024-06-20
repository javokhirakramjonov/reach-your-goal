package presentation.screen.main.mvi.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import presentation.screen.main.mvi.event.ScreenEvent
import presentation.screen.main.mvi.state.ScreenUiState
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.select_plan

@Composable
fun ScreenUi(
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PlanSelector(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                currentPlanName = uiState.currentPlan?.name ?: stringResource(Res.string.select_plan),
                plans = uiState.plans,
                planSelected = { plan ->
                    action(ScreenEvent.Input.PlanSelected(plan))
                }
            )

            Spacer(modifier = Modifier.padding(16.dp))

            WeeklySchedule(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .border(width = 2.dp, MaterialTheme.colorScheme.primary),
                scheduledTasks = uiState.scheduledTasks,
                onStatusChanged = { taskId, day, status ->
                    action(ScreenEvent.Input.StatusChanged(taskId, day, status))
                }
            )
        }
    }
}
