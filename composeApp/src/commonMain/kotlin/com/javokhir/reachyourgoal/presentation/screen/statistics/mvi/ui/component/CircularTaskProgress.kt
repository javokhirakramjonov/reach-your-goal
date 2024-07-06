package com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.ui.component

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import com.javokhir.reachyourgoal.domain.model.WeekDayTaskProgress
import com.javokhir.reachyourgoal.utils.progressColor
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.delay

@Composable
fun CircularTaskProgress(
    modifier: Modifier = Modifier,
    statusAndCounts: ImmutableList<WeekDayTaskProgress>
) {
    var animationStarted by remember(statusAndCounts) { mutableStateOf(false) }

    LaunchedEffect(statusAndCounts) {
        animationStarted = false
        delay(1000)
        animationStarted = true
    }

    val density = LocalDensity.current
    val borderWidth = with(density) { 8.dp.toPx() }
    val gap = 8f

    val maxAngle = 360f - gap * statusAndCounts.size

    val countAllTasks by remember(statusAndCounts) { mutableStateOf(statusAndCounts.sumOf { it.taskCount }) }

    val percent by animateFloatAsState(
        targetValue = if (animationStarted) 1f else 0f,
        animationSpec = TweenSpec(durationMillis = if (animationStarted) 1000 else 300)
    )

    val statusAndAngles by remember(statusAndCounts) {
        mutableStateOf(statusAndCounts.map { it.taskStatus to (maxAngle * it.taskCount / countAllTasks) })
    }

    val statusProgressColors = TaskStatus.entries.associateWith { it.progressColor() }

    Canvas(modifier) {
        var currentAngle = 0f

        statusAndAngles.forEach { (status, angle) ->
            drawArc(
                color = statusProgressColors.getValue(status),
                style = Stroke(width = borderWidth, cap = StrokeCap.Round),
                startAngle = currentAngle,
                sweepAngle = percent * angle,
                useCenter = false,
                size = Size(size.minDimension, size.minDimension)
            )

            currentAngle += percent * angle + gap
        }
    }
}