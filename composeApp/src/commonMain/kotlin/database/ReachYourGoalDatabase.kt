package database

import androidx.room.Database
import androidx.room.RoomDatabase
import dao.PlanDao
import dao.TaskAndPlanDao
import dao.TaskAndStatusDao
import dao.TaskDao
import domain.entity.Plan
import domain.entity.Task
import domain.entity.TaskAndPlan
import domain.entity.TaskAndStatus

@Database(
    entities = [
        Task::class,
        Plan::class,
        TaskAndPlan::class,
        TaskAndStatus::class
    ],
    version = 1
)
abstract class ReachYourGoalDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao
    abstract fun getPlanDao(): PlanDao
    abstract fun getTaskAndPlanDao(): TaskAndPlanDao
    abstract fun getTaskAndStatusDao(): TaskAndStatusDao

}