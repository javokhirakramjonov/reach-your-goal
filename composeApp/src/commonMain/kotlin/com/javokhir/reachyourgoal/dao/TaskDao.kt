package com.javokhir.reachyourgoal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.javokhir.reachyourgoal.domain.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getByIdAsFlow(taskId: Int): Flow<Task>

    @Query("SELECT COUNT(*) FROM tasks ORDER BY created_at")
    suspend fun count(): Int

    @Query("SELECT * FROM tasks WHERE id IN (SELECT task_id FROM task_and_week WHERE week_id = :weekId)")
    fun getAllByWeekId(weekId: Int): Flow<List<Task>>

}