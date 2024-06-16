package screen.tasks.mvi.state

import domain.Task
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val tasks: ImmutableList<Task> = emptyList<Task>().toImmutableList(),
)