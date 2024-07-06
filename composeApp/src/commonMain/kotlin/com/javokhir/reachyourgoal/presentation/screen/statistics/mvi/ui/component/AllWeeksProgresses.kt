package com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.model.WeeklyTaskProgress

@Composable
fun AllWeeksProgresses(
    modifier: Modifier = Modifier,
    weeklyTaskProgresses: List<WeeklyTaskProgress>
) {
    if (weeklyTaskProgresses.isEmpty()) return

    val height = 150.dp
    val width = 100.dp

    LazyRow(modifier = modifier) {
        items(weeklyTaskProgresses) {
            SingleProgressBar(
                modifier = Modifier.width(width),
                completedTaskCount = it.completedTaskCount,
                notCompletedTaskCount = it.notCompletedTaskCount,
                height = height,
                title = it.weekName.orEmpty()
            )

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}