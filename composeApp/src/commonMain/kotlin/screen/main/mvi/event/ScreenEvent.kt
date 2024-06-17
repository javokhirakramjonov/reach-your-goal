package screen.main.mvi.event

import domain.entity.Plan
import domain.enums.TaskStatus

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data class PlanSelected(val plan: Plan): Input
        class ScheduleChanged(day: Int, status: TaskStatus) : Input
    }

    sealed interface Command : ScreenEvent {
    }
}