package presentation.screen.plans.mvi.ui

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
import presentation.dialog.addPlan.PlanCreatorDialog
import presentation.screen.plans.mvi.event.ScreenEvent
import presentation.screen.plans.mvi.state.ScreenUiState
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.no_plans

@Composable
fun ScreenUi(
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit,
) {
    var showPlanCreatorDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            AddPlanButton {
                showPlanCreatorDialog = true
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.plans.isEmpty()) {
                Text(stringResource(Res.string.no_plans))
            } else {
                PlanList(
                    modifier = Modifier.fillMaxSize(),
                    columns = 1,
                    plans = uiState.plans,
                    onPlanClick = {
                        action(ScreenEvent.Input.OpenPlan(it))
                    },
                    onDeleteClick = {
                        action(ScreenEvent.Input.DeletePlanClicked(it))
                    }
                )
            }
        }
    }

    if (showPlanCreatorDialog) {
        PlanCreatorDialog(
            onDismissRequest = { showPlanCreatorDialog = false },
            onCreateClicked = { name, description ->
                action(ScreenEvent.Input.AddPlan(name, description))
            }
        )
    }
}