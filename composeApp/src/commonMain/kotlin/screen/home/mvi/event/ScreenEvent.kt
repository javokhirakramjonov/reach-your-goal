package screen.home.mvi.event

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data class AddTask(val name: String) : Input
        data class DeleteTask(val id: Int) : Input
    }
}