package dialog.taskSelectorForPlan.mvi.state

import domain.SelectableTask
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val planId: Int,
    val selectableTasks : ImmutableList<SelectableTask> = emptyList<SelectableTask>().toImmutableList()
)