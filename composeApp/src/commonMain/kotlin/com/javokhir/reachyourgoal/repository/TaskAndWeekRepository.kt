package com.javokhir.reachyourgoal.repository

import com.javokhir.reachyourgoal.dao.TaskAndWeekDao
import com.javokhir.reachyourgoal.domain.entity.TaskAndWeek

class TaskAndWeekRepository(
    private val taskAndWeekDao: TaskAndWeekDao
) {
    fun getAllByWeekId(id: Int) = taskAndWeekDao.getAllByWeekId(id)
    suspend fun insert(task: TaskAndWeek) = taskAndWeekDao.insert(task)
    suspend fun update(task: TaskAndWeek) = taskAndWeekDao.update(task)
    suspend fun delete(task: TaskAndWeek) = taskAndWeekDao.delete(task)
    suspend fun delete(weekId: Int, taskId: Int) = taskAndWeekDao.delete(weekId, taskId)
}