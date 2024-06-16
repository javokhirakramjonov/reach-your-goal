package screen.plan.mvi.state

import domain.Plan
import domain.Task
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val plan: Plan,
    val tasks: ImmutableList<Task> = emptyList<Task>().toImmutableList()
)