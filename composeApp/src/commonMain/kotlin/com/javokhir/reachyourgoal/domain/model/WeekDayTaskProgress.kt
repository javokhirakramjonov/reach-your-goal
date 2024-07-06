package com.javokhir.reachyourgoal.domain.model

import com.javokhir.reachyourgoal.domain.enums.TaskStatus

data class WeekDayTaskProgress(
    val taskStatus: TaskStatus,
    val taskCount: Int
)