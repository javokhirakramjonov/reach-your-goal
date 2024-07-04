package com.javokhir.reachyourgoal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.javokhir.reachyourgoal.dao.TaskAndWeekDao
import com.javokhir.reachyourgoal.dao.TaskDao
import com.javokhir.reachyourgoal.dao.TaskStateDao
import com.javokhir.reachyourgoal.dao.WeekDao
import com.javokhir.reachyourgoal.domain.entity.Task
import com.javokhir.reachyourgoal.domain.entity.TaskAndWeek
import com.javokhir.reachyourgoal.domain.entity.TaskState
import com.javokhir.reachyourgoal.domain.entity.Week

@Database(
    entities = [
        Task::class,
        Week::class,
        TaskAndWeek::class,
        TaskState::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ReachYourGoalDatabase : RoomDatabase(), DB {
    abstract fun getTaskDao(): TaskDao
    abstract fun getWeekDao(): WeekDao
    abstract fun getTaskAndWeekDao(): TaskAndWeekDao
    abstract fun getTaskStateDao(): TaskStateDao

    override fun clearAllTables() {
        super.clearAllTables()
    }

}

// FIXME: Added a hack to resolve below issue:
// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}