package com.javokhir.reachyourgoal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.javokhir.reachyourgoal.dao.PlanDao
import com.javokhir.reachyourgoal.dao.TaskAndPlanDao
import com.javokhir.reachyourgoal.dao.TaskDao
import com.javokhir.reachyourgoal.domain.entity.Plan
import com.javokhir.reachyourgoal.domain.entity.Task
import com.javokhir.reachyourgoal.domain.entity.TaskAndPlan

@Database(
    entities = [
        Task::class,
        Plan::class,
        TaskAndPlan::class,
    ],
    version = 1
)
abstract class ReachYourGoalDatabase : RoomDatabase(), DB {
    abstract fun getTaskDao(): TaskDao
    abstract fun getPlanDao(): PlanDao
    abstract fun getTaskAndPlanDao(): TaskAndPlanDao

    override fun clearAllTables() {
        super.clearAllTables()
    }

}

// FIXME: Added a hack to resolve below issue:
// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}