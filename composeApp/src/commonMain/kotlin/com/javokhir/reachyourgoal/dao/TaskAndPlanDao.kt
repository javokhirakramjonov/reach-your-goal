package com.javokhir.reachyourgoal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.javokhir.reachyourgoal.domain.entity.TaskAndPlan
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskAndPlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskAndPlan: TaskAndPlan)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskAndPlans: List<TaskAndPlan>)

    @Update
    suspend fun update(taskAndPlan: TaskAndPlan)

    @Delete
    suspend fun delete(taskAndPlan: TaskAndPlan)

    @Query("DELETE FROM task_and_plan WHERE plan_id = :planId and task_id = :taskId")
    suspend fun delete(planId: Int, taskId: Int)

    @Query("SELECT * FROM task_and_plan WHERE plan_id = :planId")
    fun getAllByPlanId(planId: Int): Flow<List<TaskAndPlan>>
}