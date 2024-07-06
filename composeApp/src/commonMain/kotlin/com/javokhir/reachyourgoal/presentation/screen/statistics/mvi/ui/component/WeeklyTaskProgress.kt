package com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.model.DailyTaskProgress
import com.javokhir.reachyourgoal.utils.composableName
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.toLocalDateTime

@Composable
fun WeeklyTaskProgress(
    modifier: Modifier = Modifier,
    dailyTaskProgresses: ImmutableList<DailyTaskProgress>
) {
    if (dailyTaskProgresses.isEmpty()) return

    val height = 150.dp

    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        dailyTaskProgresses.forEach {
            Box(
                modifier = Modifier.weight(1f),
            ) {
                if (it.dayOfWeek.isoDayNumber < today.dayOfWeek.isoDayNumber) {
                    SingleProgressBar(
                        completedTaskCount = it.completedTaskCount,
                        notCompletedTaskCount = it.notCompletedTaskCount,
                        height = height,
                        title = it.dayOfWeek.composableName().substring(0, 3)
                    )
                }
            }
        }
    }
}