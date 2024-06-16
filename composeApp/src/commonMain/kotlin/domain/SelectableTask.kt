package domain

data class SelectableTask(
    val task: Task,
    val isSelected: Boolean
)