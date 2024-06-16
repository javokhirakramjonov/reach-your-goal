package database

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

fun getDatabase(): RoomDatabase.Builder<ReachYourGoalDatabase> {
    val dbFile = NSHomeDirectory() + "/tasks.db"

    return Room
        .databaseBuilder<ReachYourGoalDatabase>(
            name = dbFile,
            factory = {
                ReachYourGoalDatabase::class.instantiateImpl()
            }
        )
}