package com.javokhir.reachyourgoal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.javokhir.reachyourgoal.domain.entity.TaskState
import com.javokhir.reachyourgoal.domain.model.DailyTaskProgress
import com.javokhir.reachyourgoal.domain.model.WeekDayTaskProgress
import com.javokhir.reachyourgoal.domain.model.WeeklyTaskProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.DayOfWeek


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

    @Query(
        "SELECT week_id as weekId, " +
                "SUM(CASE WHEN status = 'DONE' THEN 1 ELSE 0 END) as completedTaskCount, " +
                "SUM(CASE WHEN status = 'NOT_COMPLETED' THEN 1 ELSE 0 END) as notCompletedTaskCount " +
                "FROM task_states GROUP BY week_id"
    )
    fun getTaskCountsForEachWeek(): Flow<List<WeeklyTaskProgress>>

    @Query(
        "SELECT day as dayOfWeek, " +
                "SUM(CASE WHEN status = 'DONE' THEN 1 ELSE 0 END) as completedTaskCount, " +
                "SUM(CASE WHEN status = 'NOT_COMPLETED' THEN 1 ELSE 0 END) as notCompletedTaskCount " +
                "FROM task_states WHERE week_id = :weekId GROUP BY day"
    )
    fun getTaskCountsForEachDayInWeek(weekId: Int): Flow<List<DailyTaskProgress>>

    @Query(
        "SELECT status as taskStatus, COUNT(*) AS taskCount " +
                "FROM task_states " +
                "WHERE week_id = :weekId AND day = :dayOfWeek " +
                "GROUP BY status"
    )
    fun getTaskStatusCountsForWeekDay(
        weekId: Int,
        dayOfWeek: DayOfWeek
    ): Flow<List<WeekDayTaskProgress>>

}