package com.javokhir.reachyourgoal.presentation.screen.weeks.mvi.event

import com.javokhir.reachyourgoal.domain.entity.Week

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data class OpenWeek(val week: Week) : Input
    }

    sealed interface Command : ScreenEvent {
        data class OpenWeek(val week: Week) : Command
    }
}