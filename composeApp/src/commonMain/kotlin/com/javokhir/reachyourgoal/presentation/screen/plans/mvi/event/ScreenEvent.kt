package com.javokhir.reachyourgoal.presentation.screen.plans.mvi.event

import com.javokhir.reachyourgoal.domain.entity.Plan

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data class AddPlan(val name: String, val description: String?) : Input
        data class DeletePlanClicked(val plan: Plan) : Input
        data class OpenPlan(val plan: Plan) : Input
    }

    sealed interface Command : ScreenEvent {
        data class OpenPlan(val plan: Plan) : Command
    }
}