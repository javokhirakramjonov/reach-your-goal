package com.javokhir.reachyourgoal.repository

import com.javokhir.reachyourgoal.dao.TaskStateDao
import com.javokhir.reachyourgoal.domain.entity.TaskState

class TaskStateRepository(
    private val taskStateDao: TaskStateDao
) {
    fun getAllByTaskIdAndWeekId(taskId: Int, weekId: Int) =
        taskStateDao.getAllByTaskIdAndWeekId(taskId, weekId)

    suspend fun insert(taskState: TaskState) = taskStateDao.insert(taskState)
    suspend fun deleteAllByTaskIdAndWeekId(taskId: Int, weekId: Int) =
        taskStateDao.deleteAllByTaskIdAndWeekId(taskId, weekId)

    suspend fun update(taskState: TaskState) = taskStateDao.update(taskState)
}