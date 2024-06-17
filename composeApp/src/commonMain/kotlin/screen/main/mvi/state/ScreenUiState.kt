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
    //emptyList<Pair<Task, ImmutableList<TaskStatus>>>().toImmutableList()
        listOf(
            Task(1, "Task 1", "Task 1 description") to randomStatuses(),
            Task(2, "Task 2", "Task 2 description") to randomStatuses(),
            Task(3, "Task 3", "Task 3 description") to randomStatuses(),
            Task(4, "Task 4", "Task 4 description") to randomStatuses(),
            Task(5, "Task 5", "Task 5 description") to randomStatuses(),
            Task(6, "Task 6", "Task 6 description") to randomStatuses(),
            Task(7, "Task 7", "Task 7 description") to randomStatuses(),
            Task(8, "Task 8", "Task 8 description") to randomStatuses(),
            Task(9, "Task 9", "Task 9 description") to randomStatuses(),
            Task(0, "Task 10", "Task 10 description") to randomStatuses(),
            Task(11, "Task 11", "Task 11 description") to randomStatuses(),
            Task(12, "Task 12", "Task 12 description") to randomStatuses(),
            Task(13, "Task 13", "Task 13 description") to randomStatuses(),
            Task(14, "Task 14", "Task 14 description") to randomStatuses(),
            Task(15, "Task 15", "Task 15 description") to randomStatuses(),
            Task(16, "Task 16", "Task 16 description") to randomStatuses(),
            Task(17, "Task 17", "Task 17 description") to randomStatuses(),
            Task(18, "Task 18", "Task 18 description") to randomStatuses(),
            Task(19, "Task 19", "Task 19 description") to randomStatuses(),
            Task(20, "Task 20", "Task 20 description") to randomStatuses(),
        ).toImmutableList()
)

private fun randomStatuses() : ImmutableList<TaskStatus> {
    val statuses = mutableListOf<TaskStatus>()
    for (i in 0..6) {
        statuses.add(TaskStatus.entries.random())
    }
    return statuses.toImmutableList()
}