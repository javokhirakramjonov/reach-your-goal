package com.javokhir.reachyourgoal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javokhir.reachyourgoal.domain.entity.Week
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

@Dao
interface WeekDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(week: Week)

    @Query("SELECT * FROM weeks")
    fun getAll(): Flow<List<Week>>

    @Query("SELECT * FROM weeks WHERE id = :id")
    suspend fun getById(id: Int): Week

    @Query("SELECT start_date FROM weeks ORDER BY start_date DESC LIMIT 1")
    suspend fun getLastWeekStartDate(): LocalDate?

    @Query("SELECT * FROM weeks WHERE start_date = :date")
    suspend fun getWeekByStartDate(date: LocalDate): Week?
}