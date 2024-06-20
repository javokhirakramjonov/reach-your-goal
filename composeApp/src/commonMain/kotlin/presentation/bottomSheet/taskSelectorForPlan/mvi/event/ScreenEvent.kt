package presentation.bottomSheet.taskSelectorForPlan.mvi.event

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data object SaveClicked : Input

        data class TaskSelectionChanged(val taskId: Int, val isSelected: Boolean) : Input
    }

    sealed interface Command : ScreenEvent {
        data object Exit: Command
    }
}