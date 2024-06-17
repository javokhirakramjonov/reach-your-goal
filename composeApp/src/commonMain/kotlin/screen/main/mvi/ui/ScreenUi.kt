package screen.main.mvi.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import screen.main.mvi.event.ScreenEvent
import screen.main.mvi.state.ScreenUiState

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
                currentPlanName = uiState.currentPlan?.name ?: "Select a plan",
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
                    .border(width = 2.dp, Color.Black),
                scheduledTasks = uiState.scheduledTasks,
                onScheduleChanged = { day, status ->
                    action(ScreenEvent.Input.ScheduleChanged(day, status))
                }
            )
        }
    }
}
