package com.javokhir.reachyourgoal.domain.model

import com.javokhir.reachyourgoal.domain.entity.Task
import com.javokhir.reachyourgoal.domain.enums.TaskStatus

data class SelectableTask(
    val task: Task,
    val isSelected: Boolean,
    val statuses: List<TaskStatus>
)