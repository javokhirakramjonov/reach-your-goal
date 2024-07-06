package com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.model.DailyTaskProgress
import com.javokhir.reachyourgoal.utils.composableName
import kotlinx.collections.immutable.ImmutableList

@Composable
fun WeeklyTaskProgress(
    modifier: Modifier = Modifier,
    dailyTaskProgresses: ImmutableList<DailyTaskProgress>
) {
    if (dailyTaskProgresses.isEmpty()) return

    val height = 150.dp

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        dailyTaskProgresses.forEach {
            SingleProgressBar(
                modifier = Modifier.weight(1f),
                completedTaskCount = it.completedTaskCount,
                notCompletedTaskCount = it.notCompletedTaskCount,
                height = height,
                title = it.dayOfWeek.composableName().substring(0, 3)
            )
        }
    }
}