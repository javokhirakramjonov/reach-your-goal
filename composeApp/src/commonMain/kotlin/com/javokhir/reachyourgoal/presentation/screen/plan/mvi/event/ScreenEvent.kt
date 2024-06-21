package com.javokhir.reachyourgoal.presentation.screen.plan.mvi.event

import com.javokhir.reachyourgoal.presentation.screen.plan.domain.ScreenError

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data object UpdateTasksClicked : Input
        data object DeletePlan : Input
        data object Exit : Input

        data class PlanChanged(val name: String, val description: String?) : Input
    }

    sealed interface Command : ScreenEvent {
        data object ShowTaskSelector : Command
        data object Exit : Command

        data class ShowErrorSnackbar(val error: ScreenError) : Command
    }
}