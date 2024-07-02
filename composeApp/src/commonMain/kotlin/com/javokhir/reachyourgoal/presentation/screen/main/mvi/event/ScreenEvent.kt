package com.javokhir.reachyourgoal.presentation.screen.main.mvi.event

import com.javokhir.reachyourgoal.domain.entity.Week
import com.javokhir.reachyourgoal.domain.enums.TaskStatus

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data class WeekSelected(val week: Week) : Input
        data class StatusChanged(val taskId: Int, val day: Int, val status: TaskStatus) : Input
    }

    sealed interface Command : ScreenEvent
}