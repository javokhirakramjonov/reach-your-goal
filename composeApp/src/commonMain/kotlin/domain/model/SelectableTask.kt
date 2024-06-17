package domain.model

import domain.entity.Task

data class SelectableTask(
    val task: Task,
    val isSelected: Boolean
)