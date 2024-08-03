package com.javokhir.reachyourgoal.locale.components

import com.javokhir.reachyourgoal.domain.enums.TaskStatus

data class TaskStatusNames(
    val getName: (TaskStatus) -> String
)
