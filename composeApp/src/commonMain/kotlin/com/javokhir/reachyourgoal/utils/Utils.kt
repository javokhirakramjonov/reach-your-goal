package com.javokhir.reachyourgoal.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.javokhir.reachyourgoal.domain.enums.TaskStatus

suspend fun <T, R> T.letCoroutine(block: suspend (T) -> R): R {
    return block(this)
}

@Composable
fun pxToDp(px: Float): Dp {
    val density = LocalDensity.current
    return with(density) { px.toDp() }
}

fun weeklyStatusTemplate(): List<TaskStatus> = List(7) { TaskStatus.NOT_STARTED }