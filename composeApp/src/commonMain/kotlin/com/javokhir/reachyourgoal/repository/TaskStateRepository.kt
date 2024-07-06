package com.javokhir.reachyourgoal.repository

import com.javokhir.reachyourgoal.dao.TaskStateDao
import com.javokhir.reachyourgoal.domain.entity.TaskState
import kotlinx.datetime.DayOfWeek

class TaskStateRepository(
    private val taskStateDao: TaskStateDao
) {
    fun getAllByTaskIdAndWeekId(taskId: Int, weekId: Int) =
        taskStateDao.getAllByTaskIdAndWeekId(taskId, weekId)

    suspend fun insert(taskState: TaskState) = taskStateDao.insert(taskState)
    suspend fun deleteAllByTaskIdAndWeekId(taskId: Int, weekId: Int) =
        taskStateDao.deleteAllByTaskIdAndWeekId(taskId, weekId)

    suspend fun update(taskState: TaskState) = taskStateDao.update(taskState)

    fun getTaskCountsForEachWeek() = taskStateDao.getTaskCountsForEachWeek()

    fun getTaskCountsForEachDayInWeek(weekId: Int) =
        taskStateDao.getTaskCountsForEachDayInWeek(weekId)

    fun getTaskStatusCountsForWeekDay(weekId: Int, dayOfWeek: DayOfWeek) =
        taskStateDao.getTaskStatusCountsForWeekDay(weekId, dayOfWeek)
}