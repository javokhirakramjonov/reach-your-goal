package com.javokhir.reachyourgoal.domain.model

import kotlinx.datetime.DayOfWeek

data class DailyTaskProgress(
    val dayOfWeek: DayOfWeek,
    val completedTaskCount: Int,
    val notCompletedTaskCount: Int
)