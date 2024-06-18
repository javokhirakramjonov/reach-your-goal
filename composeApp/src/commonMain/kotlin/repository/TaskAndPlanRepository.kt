package repository

import dao.TaskAndPlanDao
import domain.entity.TaskAndPlan
import kotlinx.coroutines.flow.map
import utils.transformCollection

class TaskAndPlanRepository(
    private val taskAndPlanDao: TaskAndPlanDao
) {
    fun getAllByPlanId(id: Int) = taskAndPlanDao.getAllByPlanId(id).map { it.transformCollection() }
    suspend fun insert(task: TaskAndPlan) = taskAndPlanDao.insert(task)
    suspend fun update(task: TaskAndPlan) = taskAndPlanDao.update(task)
    suspend fun delete(task: TaskAndPlan) = taskAndPlanDao.delete(task)
    suspend fun delete(planId: Int, taskId: Int) = taskAndPlanDao.delete(planId, taskId)
}