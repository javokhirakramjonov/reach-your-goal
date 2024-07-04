package com.javokhir.reachyourgoal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.javokhir.reachyourgoal.domain.entity.TaskState
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskStateDao {

    @Query("SELECT * FROM task_states WHERE task_id = :taskId AND week_id = :weekId")
    fun getAllByTaskIdAndWeekId(taskId: Int, weekId: Int): Flow<List<TaskState>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskState: TaskState)

    @Query("DELETE FROM task_states WHERE task_id = :taskId AND week_id = :weekId")
    suspend fun deleteAllByTaskIdAndWeekId(taskId: Int, weekId: Int)

    @Update
    suspend fun update(taskState: TaskState)
}