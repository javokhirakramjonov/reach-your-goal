package com.javokhir.reachyourgoal.repository

import com.javokhir.reachyourgoal.dao.PlanDao
import com.javokhir.reachyourgoal.domain.entity.Plan

class PlanRepository(
    private val planDao: PlanDao
) {
    fun getAll() = planDao.getAll()
    fun getById(id: Int) = planDao.getById(id)
    suspend fun insert(plan: Plan) = planDao.insert(plan)
    suspend fun update(plan: Plan) = planDao.update(plan)
    suspend fun delete(plan: Plan) = planDao.delete(plan)
}