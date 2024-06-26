package com.javokhir.reachyourgoal.repository

import com.javokhir.reachyourgoal.dao.TaskDao
import com.javokhir.reachyourgoal.domain.entity.Task

class TaskRepository(
    private val taskDao: TaskDao
) {
    fun getAll() = taskDao.getAll()
    suspend fun getById(id: Int) = taskDao.getById(id)
    suspend fun insert(task: Task) = taskDao.insert(task)
    suspend fun update(task: Task) = taskDao.update(task)
    suspend fun delete(task: Task) = taskDao.delete(task)
    suspend fun count() = taskDao.count()
}