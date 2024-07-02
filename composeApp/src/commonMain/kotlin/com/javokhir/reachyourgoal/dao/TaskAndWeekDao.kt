package com.javokhir.reachyourgoal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.javokhir.reachyourgoal.domain.entity.TaskAndWeek
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskAndWeekDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskAndWeek: TaskAndWeek)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskAndWeeks: List<TaskAndWeek>)

    @Update
    suspend fun update(taskAndWeek: TaskAndWeek)

    @Delete
    suspend fun delete(taskAndWeek: TaskAndWeek)

    @Query("DELETE FROM task_and_week WHERE week_id = :weekId and task_id = :taskId")
    suspend fun delete(weekId: Int, taskId: Int)

    @Query("SELECT * FROM task_and_week WHERE week_id = :weekId")
    fun getAllByWeekId(weekId: Int): Flow<List<TaskAndWeek>>
}