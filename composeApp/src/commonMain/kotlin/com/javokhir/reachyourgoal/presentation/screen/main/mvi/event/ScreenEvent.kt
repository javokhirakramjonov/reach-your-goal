package com.javokhir.reachyourgoal.presentation.screen.main.mvi.event

import com.javokhir.reachyourgoal.domain.entity.TaskState
import com.javokhir.reachyourgoal.domain.entity.Week

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data class WeekSelected(val week: Week) : Input
        data class StatusChanged(val taskState: TaskState) : Input
    }

    sealed interface Command : ScreenEvent
}