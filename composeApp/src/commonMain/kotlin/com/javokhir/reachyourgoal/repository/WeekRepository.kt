package com.javokhir.reachyourgoal.repository

import com.javokhir.reachyourgoal.dao.WeekDao
import com.javokhir.reachyourgoal.domain.entity.Week
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

class WeekRepository(
    private val weekDao: WeekDao
) {
    fun getAll() = weekDao.getAll()
    suspend fun getById(id: Int) = weekDao.getById(id)
    suspend fun insert(week: Week) = weekDao.insert(week)

    suspend fun getLastWeekStartDate(): LocalDate {
        val lastWeekFromDate = weekDao.getLastWeekStartDate()

        return if (lastWeekFromDate != null) {
            lastWeekFromDate
        } else {
            val today =
                Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

            val lastWeekMonday = today
                .minus(1, DateTimeUnit.WEEK)
                .minus(today.dayOfWeek.isoDayNumber - 1, DateTimeUnit.DAY)

            lastWeekMonday
        }
    }
}