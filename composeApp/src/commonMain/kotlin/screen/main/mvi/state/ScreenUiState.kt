package screen.main.mvi.state

import domain.entity.Plan
import domain.entity.Task
import domain.enums.TaskStatus
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val plans: ImmutableList<Plan> = emptyList<Plan>().toImmutableList(),
    val currentPlan: Plan? = null,
    val scheduledTasks: ImmutableList<Pair<Task, ImmutableList<TaskStatus>>> =
        emptyList<Pair<Task, ImmutableList<TaskStatus>>>().toImmutableList()
)