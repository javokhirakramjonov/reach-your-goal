package domain.model

import domain.entity.TaskAndPlan
import domain.enums.TaskStatus
import utils.Transformable

data class TaskAndPlanDto(
    val taskId: Int,
    val planId: Int,
    val statuses: List<TaskStatus> = weeklyStatusTemplate()
) : Transformable<TaskAndPlan> {
    override fun transform(): TaskAndPlan {
        return TaskAndPlan(
            taskId = taskId,
            planId = planId,
            statuses = statuses.joinToString(",")
        )
    }
}

private fun weeklyStatusTemplate(): List<TaskStatus> = List(7) { TaskStatus.NOT_STARTED }