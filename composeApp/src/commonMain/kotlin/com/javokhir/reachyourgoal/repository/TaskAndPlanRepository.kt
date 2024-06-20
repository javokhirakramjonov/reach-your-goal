package com.javokhir.reachyourgoal.repository

import com.javokhir.reachyourgoal.dao.TaskAndPlanDao
import com.javokhir.reachyourgoal.domain.entity.TaskAndPlan
import com.javokhir.reachyourgoal.utils.transformCollection
import kotlinx.coroutines.flow.map

class TaskAndPlanRepository(
    private val taskAndPlanDao: TaskAndPlanDao
) {
    fun getAllByPlanId(id: Int) = taskAndPlanDao.getAllByPlanId(id).map { it.transformCollection() }
    suspend fun insert(task: TaskAndPlan) = taskAndPlanDao.insert(task)
    suspend fun update(task: TaskAndPlan) = taskAndPlanDao.update(task)
    suspend fun delete(task: TaskAndPlan) = taskAndPlanDao.delete(task)
    suspend fun delete(planId: Int, taskId: Int) = taskAndPlanDao.delete(planId, taskId)
}