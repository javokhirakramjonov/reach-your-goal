package screen.template.mvi.event

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
    }

    sealed interface Command : ScreenEvent {
    }
}