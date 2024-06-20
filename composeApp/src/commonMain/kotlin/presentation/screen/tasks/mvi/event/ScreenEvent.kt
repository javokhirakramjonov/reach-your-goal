package presentation.screen.tasks.mvi.event

import domain.entity.Task

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data class AddTask(val name: String, val description: String?) : Input
        data class DeleteTaskClicked(val task: Task) : Input
        data class OpenTask(val task: Task) : Input
    }

    sealed interface Command : ScreenEvent {
        data class OpenTask(val task: Task) : Command
    }
}