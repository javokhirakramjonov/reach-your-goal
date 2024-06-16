package dialog.bottomSheet.taskSelectorForPlan.mvi.event

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data object TasksChanged : Input

        data class TaskSelectionChanged(val taskId: Int, val isSelected: Boolean) : Input
    }

    sealed interface Command : ScreenEvent
}