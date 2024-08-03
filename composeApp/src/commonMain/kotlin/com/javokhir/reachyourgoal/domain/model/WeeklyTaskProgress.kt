package com.javokhir.reachyourgoal.domain.model

import androidx.compose.runtime.Composable
import kotlin.properties.Delegates

data class WeeklyTaskProgress(
    val weekId: Int,
    val completedTaskCount: Int,
    val notCompletedTaskCount: Int
) {
    var getWeekName: @Composable () -> String by Delegates.notNull()
}