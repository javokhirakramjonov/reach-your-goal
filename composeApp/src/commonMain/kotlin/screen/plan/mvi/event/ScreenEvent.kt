package screen.plan.mvi.event

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data object UpdateTasksClicked : Input
    }

    sealed interface Command : ScreenEvent {
        data object ShowTaskSelector : Command
    }
}