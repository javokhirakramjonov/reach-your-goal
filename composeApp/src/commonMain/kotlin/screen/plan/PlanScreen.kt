package screen.plan

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dialog.bottomSheet.taskSelectorForPlan.TaskSelectorForPlan
import domain.Plan
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf
import screen.plan.mvi.event.ScreenEvent
import screen.plan.mvi.state.ScreenUiState
import screen.plan.mvi.ui.ScreenUi

class PlanScreen(
    private val plan: Plan
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        val coroutineScope = rememberCoroutineScope()

        val viewModel =
            koinScreenModel<PlanScreenViewModel>(parameters = { parametersOf(ScreenUiState(plan)) })
        val uiState by viewModel.uiState.collectAsState()

        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(Unit) {
            viewModel.commands.collectLatest { command ->
                when (command) {
                    ScreenEvent.Command.ShowTaskSelector -> coroutineScope.launch {
                        bottomSheetNavigator.show(TaskSelectorForPlan(plan.id))
                    }

                    ScreenEvent.Command.Exit -> navigator.pop()
                    is ScreenEvent.Command.ShowErrorSnackbar -> coroutineScope.launch {
                        snackbarHostState.showSnackbar(command.message)
                    }
                }
            }
        }

        ScreenUi(
            snackbarHostState = snackbarHostState,
            uiState = uiState,
            action = viewModel::action
        )
    }
}