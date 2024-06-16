package screen.plans.mvi.event

import domain.Plan

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data class AddAndOpenPlan(val name: String, val description: String?) : Input
        data class DeletePlan(val id: Int) : Input
        data class OpenPlan(val plan: Plan) : Input
    }

    sealed interface Command : ScreenEvent {
        data class OpenPlan(val plan: Plan) : Command
    }
}