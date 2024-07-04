package com.javokhir.reachyourgoal.repository

import com.javokhir.reachyourgoal.dao.TaskDao
import com.javokhir.reachyourgoal.domain.entity.Task

class TaskRepository(
    private val taskDao: TaskDao
) {
    fun getAll() = taskDao.getAll()
    fun getAllByWeekId(weekId: Int) = taskDao.getAllByWeekId(weekId)
    suspend fun insert(task: Task) = taskDao.insert(task)
    suspend fun update(task: Task) = taskDao.update(task)
    suspend fun delete(task: Task) = taskDao.delete(task)
    suspend fun count() = taskDao.count()
}