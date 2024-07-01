package com.javokhir.reachyourgoal.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import com.javokhir.reachyourgoal.domain.model.DailyProgress
import com.javokhir.reachyourgoal.domain.model.WeekDay
import com.javokhir.reachyourgoal.utils.composableName
import com.javokhir.reachyourgoal.utils.progressColor
import kotlinx.collections.immutable.ImmutableList
import kotlin.math.max

@Composable
fun WeeklyTaskProgress(
    modifier: Modifier = Modifier,
    dailyProgresses: ImmutableList<DailyProgress>
) {
    if (dailyProgresses.isEmpty()) return

    val extrema = dailyProgresses.maxOf { max(it.completedTaskCount, it.notCompletedTaskCount) }

    val height = 100.dp
    val width = 10.dp

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            WeekDay.entries.forEachIndexed { index, weekDay ->
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    dailyProgresses.getOrNull(index)?.let { progress ->
                        DailyProgressBar(
                            progress = progress,
                            extrema = extrema,
                            day = weekDay,
                            width = width,
                            height = height
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        StatusSpecifiers(size = width)
    }
}

@Composable
private fun DailyProgressBar(
    modifier: Modifier = Modifier,
    progress: DailyProgress,
    extrema: Int,
    day: WeekDay,
    height: Dp,
    width: Dp
) {
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()

    val countHeight by remember {
        mutableStateOf(
            with(density) {
                textMeasurer.measure("0").size.height.toDp()
            }
        )
    }

    val completedHeight = height * progress.completedTaskCount / extrema
    val notCompletedHeight = height * progress.notCompletedTaskCount / extrema

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.height(height * 2 + countHeight * 3),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (progress.completedTaskCount > 0) Text(text = progress.completedTaskCount.toString())

                Box(
                    modifier = Modifier
                        .width(width)
                        .height(completedHeight)
                        .clip(RoundedCornerShape(10.dp))
                        .background(TaskStatus.DONE.progressColor())
                )
            }

            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .width(width)
                        .height(notCompletedHeight)
                        .clip(RoundedCornerShape(10.dp))
                        .background(TaskStatus.NOT_COMPLETED.progressColor())
                )

                if (progress.notCompletedTaskCount > 0) Text(text = progress.notCompletedTaskCount.toString())
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = day.composableName().substring(0, 3))
    }
}

@Composable
private fun StatusSpecifiers(
    modifier: Modifier = Modifier,
    size: Dp
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(size)
                    .clip(RoundedCornerShape(4.dp))
                    .background(TaskStatus.DONE.progressColor())
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Completed")
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(size)
                    .clip(RoundedCornerShape(4.dp))
                    .background(TaskStatus.NOT_COMPLETED.progressColor())
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Not Completed")
        }
    }

}