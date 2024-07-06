package com.javokhir.reachyourgoal.domain.model

data class WeeklyTaskProgress(
    val weekId: Int,
    val completedTaskCount: Int,
    val notCompletedTaskCount: Int
) {
    var weekName: String? = null
}