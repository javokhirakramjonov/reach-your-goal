package com.javokhir.reachyourgoal.presentation.screen.week.mvi.event

import com.javokhir.reachyourgoal.presentation.screen.week.domain.ScreenError

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data object UpdateTasksClicked : Input
        data object Exit : Input
    }

    sealed interface Command : ScreenEvent {
        data object ShowTaskSelector : Command
        data object Exit : Command

        data class ShowErrorSnackbar(val error: ScreenError) : Command
    }
}