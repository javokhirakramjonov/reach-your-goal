package com.javokhir.reachyourgoal.presentation.screen.weeks.mvi.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.javokhir.reachyourgoal.presentation.screen.weeks.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.weeks.mvi.state.ScreenUiState
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.no_weeks

@Composable
fun ScreenUi(
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.weeks.isEmpty()) {
                Text(stringResource(Res.string.no_weeks))
            } else {
                WeekList(
                    modifier = Modifier.fillMaxSize(),
                    columns = 1,
                    weeks = uiState.weeks,
                    onWeekClick = {
                        action(ScreenEvent.Input.OpenWeek(it))
                    }
                )
            }
        }
    }
}