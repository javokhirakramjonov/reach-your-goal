package com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.presentation.component.WeeklyTaskProgress
import com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.state.ScreenUiState

@Composable
fun ScreenUi(
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Today's Statistics", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(20.dp))

            StatisticsOfToday(
                modifier = Modifier.fillMaxWidth(),
                taskStatuses = uiState.taskStatusesOfToday
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Week's Statistics", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(20.dp))

            WeeklyTaskProgress(
                modifier = Modifier.fillMaxWidth(),
                dailyProgresses = uiState.dailyProgressesOfWeek
            )
        }
    }
}