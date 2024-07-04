package com.javokhir.reachyourgoal.domain.model

import com.javokhir.reachyourgoal.domain.entity.Task

data class SelectableTask(
    val task: Task,
    val isSelected: Boolean,
)