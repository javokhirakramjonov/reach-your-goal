package com.javokhir.reachyourgoal.presentation.screen.main.mvi.ui

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
import com.javokhir.reachyourgoal.presentation.screen.main.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.main.mvi.state.ScreenUiState
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.select_week

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
            WeekSelector(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                currentWeekName = uiState.currentWeek?.getNameComposable()
                    ?: stringResource(Res.string.select_week),
                weeks = uiState.weeks,
                weekSelected = { week ->
                    action(ScreenEvent.Input.WeekSelected(week))
                }
            )

            Spacer(modifier = Modifier.padding(16.dp))

            WeeklySchedule(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .border(width = 2.dp, MaterialTheme.colorScheme.primary),
                scheduledTasks = uiState.scheduledTasks,
                onStatusChanged = {
                    action(ScreenEvent.Input.StatusChanged(it))
                }
            )
        }
    }
}
