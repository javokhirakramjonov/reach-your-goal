package com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek.mvi.ui.ScreenUi
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.parameter.parametersOf

class TaskSelectorForWeek(
    private val weekId: Int
) : Screen {
    @Composable
    override fun Content() {

        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        val viewModel = koinScreenModel<TaskSelectorForWeekViewModel>(parameters = {
            parametersOf(
                ScreenUiState(weekId)
            )
        })
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel
                .commands
                .collectLatest { command ->
                    when (command) {
                        ScreenEvent.Command.Exit -> bottomSheetNavigator.hide()
                    }
                }
        }

        ScreenUi(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .height(500.dp),
            uiState = uiState,
            action = viewModel::action
        )
    }
}