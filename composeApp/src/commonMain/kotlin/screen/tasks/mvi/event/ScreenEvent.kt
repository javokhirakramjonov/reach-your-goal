package screen.tasks.mvi.event

import domain.entity.Task

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data class AddTask(val name: String, val description: String?) : Input
        data class DeleteTask(val id: Int) : Input
        data class OpenTask(val task: Task) : Input
    }

    sealed interface Command : ScreenEvent {
        data class OpenTask(val task: Task) : Command
    }
}