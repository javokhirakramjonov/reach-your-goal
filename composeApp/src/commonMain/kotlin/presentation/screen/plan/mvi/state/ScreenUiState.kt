package presentation.screen.plan.mvi.state

import domain.entity.Plan
import domain.entity.Task
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val plan: Plan,
    val tasks: ImmutableList<Task> = emptyList<Task>().toImmutableList()
)