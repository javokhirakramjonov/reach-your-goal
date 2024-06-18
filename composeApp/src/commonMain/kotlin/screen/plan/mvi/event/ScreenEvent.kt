package screen.plan.mvi.event

import screen.plan.domain.ScreenError

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data object UpdateTasksClicked : Input
        data object DeletePlan: Input
        data class PlanChanged(val name: String, val description: String?) : Input
    }

    sealed interface Command : ScreenEvent {
        data object ShowTaskSelector : Command
        data object Exit : Command

        data class ShowErrorSnackbar(val error: ScreenError) : Command
    }
}