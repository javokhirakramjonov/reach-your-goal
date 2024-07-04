package com.javokhir.reachyourgoal.presentation.screen.main.model

import com.javokhir.reachyourgoal.domain.entity.Task
import com.javokhir.reachyourgoal.domain.entity.TaskState
import kotlinx.collections.immutable.ImmutableList

data class TaskAndStates(
    val task: Task,
    val states: ImmutableList<TaskState>
)