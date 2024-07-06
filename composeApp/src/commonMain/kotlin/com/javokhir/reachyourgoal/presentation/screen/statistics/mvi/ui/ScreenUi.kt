package com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.ui.component.AllWeeksProgresses
import com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.ui.component.WeeklyTaskProgress
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.statistics_all
import reach_your_goal.composeapp.generated.resources.statistics_today
import reach_your_goal.composeapp.generated.resources.statistics_week

@Composable
fun ScreenUi(
    uiState: ScreenUiState
) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.statistics_today),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            StatisticsOfToday(
                modifier = Modifier.fillMaxWidth(),
                taskStatuses = uiState.taskStatusesOfToday
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(Res.string.statistics_week),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            WeeklyTaskProgress(
                modifier = Modifier.fillMaxWidth(),
                dailyTaskProgresses = uiState.dailyTaskProgressesOfWeek
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(Res.string.statistics_all),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            AllWeeksProgresses(
                modifier = Modifier.fillMaxWidth(),
                weeklyTaskProgresses = uiState.weeklyProgresses
            )
        }
    }
}