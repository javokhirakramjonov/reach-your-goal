package screen.plans.mvi.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dialog.addTask.AddTaskDialog
import screen.plans.mvi.event.ScreenEvent
import screen.plans.mvi.state.ScreenUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenUi(
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit,
) {
    var showAddPlanDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Plans Screen") })
        },
        floatingActionButton = {
            AddTaskButton {
                showAddPlanDialog = true
            }
        }
    ) {
        PlanList(
            modifier = Modifier.padding(it),
            columns = 1,
            plans = uiState.plans
        ) {
            action(ScreenEvent.Input.OpenPlan(it))
        }
    }

    if(showAddPlanDialog) {
        AddTaskDialog(
            onDismissRequest = { showAddPlanDialog = false },
            onCreateClicked = { name, description ->
                action(ScreenEvent.Input.AddPlan(name, description))
            }
        )
    }
}