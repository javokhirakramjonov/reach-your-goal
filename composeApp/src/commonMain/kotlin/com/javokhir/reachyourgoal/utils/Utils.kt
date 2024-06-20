package com.javokhir.reachyourgoal.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

suspend fun <T, R> T.letCoroutine(block: suspend (T) -> R): R {
    return block(this)
}

@Composable
fun pxToDp(px: Float): Dp {
    val density = LocalDensity.current
    return with(density) { px.toDp() }
}