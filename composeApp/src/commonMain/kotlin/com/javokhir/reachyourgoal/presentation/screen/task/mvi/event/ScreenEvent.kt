package com.javokhir.reachyourgoal.presentation.screen.task.mvi.event

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data object DeleteTask : Input

        data class TaskChanged(val name: String, val description: String?) : Input
    }

    sealed interface Command : ScreenEvent {
        data object Exit : Command
    }
}