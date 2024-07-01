package com.javokhir.reachyourgoal.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.task_status_done
import reach_your_goal.composeapp.generated.resources.task_status_not_completed
import reach_your_goal.composeapp.generated.resources.task_status_not_mandatory
import reach_your_goal.composeapp.generated.resources.task_status_not_started

@Composable
fun TaskStatus.icon(
    modifier: Modifier = Modifier
) {
    val positiveBackgroundColor = Color(0xFFACE1AF)
    val positiveTintColor = Color(0xFF177245)

    val negativeBackgroundColor = Color(0xFFE1ACAF)
    val negativeTintColor = Color(0xFFD32F2F)


    val backgroundColor = when (this) {
        TaskStatus.NOT_STARTED -> MaterialTheme.colorScheme.background
        TaskStatus.NOT_MANDATORY -> positiveBackgroundColor
        TaskStatus.DONE -> positiveBackgroundColor
        TaskStatus.NOT_COMPLETED -> negativeBackgroundColor
    }
    val tintColor = when (this) {
        TaskStatus.NOT_STARTED -> positiveTintColor
        TaskStatus.NOT_MANDATORY -> positiveTintColor
        TaskStatus.DONE -> positiveTintColor
        TaskStatus.NOT_COMPLETED -> negativeTintColor
    }

    val boxModifier = Modifier
        .size(24.dp)
        .clip(CircleShape)
        .border(1.dp, tintColor, CircleShape)
        .background(backgroundColor)
        .padding(4.dp)

    val imageVector = when (this) {
        TaskStatus.NOT_STARTED -> null
        TaskStatus.NOT_MANDATORY -> Icons.Default.Add
        TaskStatus.DONE -> Icons.Default.DoneAll
        TaskStatus.NOT_COMPLETED -> Icons.Default.Remove
    }

    Box(modifier.then(boxModifier)) {
        if (imageVector != null) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = tintColor
            )
        }
    }
}

@Composable
fun TaskStatus.composableName(): String {
    val resourceName = when (this) {
        TaskStatus.NOT_STARTED -> Res.string.task_status_not_started
        TaskStatus.NOT_MANDATORY -> Res.string.task_status_not_mandatory
        TaskStatus.DONE -> Res.string.task_status_done
        TaskStatus.NOT_COMPLETED -> Res.string.task_status_not_completed
    }

    return stringResource(resourceName)
}

@Composable
fun TaskStatus.progressColor(): Color {
    return when (this) {
        TaskStatus.NOT_STARTED -> Color(0xFFfacc15)
        TaskStatus.DONE -> Color(0xFF4ade80)
        TaskStatus.NOT_COMPLETED -> Color(0xFFf87171)
        else -> Color.Transparent
    }
}