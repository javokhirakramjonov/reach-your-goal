package dialog.taskSelectorForPlan

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import dialog.taskSelectorForPlan.mvi.state.ScreenUiState
import dialog.taskSelectorForPlan.mvi.ui.ScreenUi
import org.koin.core.parameter.parametersOf

class TaskSelectorForPlan(
    private val planId: Int
) : Screen {
    @Composable
    override fun Content() {

        val viewModel = koinScreenModel<TaskSelectorForPlanViewModel>(parameters = { parametersOf(ScreenUiState(planId)) })
        val uiState by viewModel.uiState.collectAsState()

        ScreenUi(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .heightIn(min = 400.dp),
            uiState = uiState,
            action = viewModel::action
        )
    }
}