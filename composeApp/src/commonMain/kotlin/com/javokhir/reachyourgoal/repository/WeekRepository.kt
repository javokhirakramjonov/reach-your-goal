package com.javokhir.reachyourgoal.repository

import com.javokhir.reachyourgoal.dao.WeekDao
import com.javokhir.reachyourgoal.domain.entity.Week

class WeekRepository(
    private val weekDao: WeekDao
) {
    fun getAll() = weekDao.getAll()
    fun getById(id: Int) = weekDao.getById(id)
    suspend fun insert(week: Week) = weekDao.insert(week)
    suspend fun update(week: Week) = weekDao.update(week)
    suspend fun delete(week: Week) = weekDao.delete(week)
}