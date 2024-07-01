package com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import com.javokhir.reachyourgoal.presentation.component.CircularTaskProgress
import com.javokhir.reachyourgoal.utils.composableName
import com.javokhir.reachyourgoal.utils.progressColor
import kotlinx.collections.immutable.ImmutableList

@Composable
fun StatisticsOfToday(
    modifier: Modifier = Modifier,
    taskStatuses: ImmutableList<Pair<TaskStatus, Int>>,
) {
    val filteredStatusAndCounts = taskStatuses.filter { it.second > 0 }

    if (filteredStatusAndCounts.isEmpty()) return

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Row {
            Spacer(modifier = Modifier.weight(1f))
            CircularTaskProgress(
                modifier = Modifier
                    .weight(2f)
                    .aspectRatio(1f),
                statusAndCounts = taskStatuses
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Column {
            taskStatuses.forEach { (status, count) ->
                Text(
                    text = "${status.composableName()}: $count",
                    color = status.progressColor()
                )
            }
        }
    }
}