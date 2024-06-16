package database

import androidx.room.Database
import androidx.room.RoomDatabase
import dao.PlanDao
import dao.TaskAndPlanDao
import dao.TaskDao
import domain.Plan
import domain.Task
import domain.TaskAndPlan

@Database(
    entities = [
        Task::class,
        Plan::class,
        TaskAndPlan::class
    ],
    version = 1
)
abstract class ReachYourGoalDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao
    abstract fun getPlanDao(): PlanDao
    abstract fun getTaskAndPlanDao(): TaskAndPlanDao

}