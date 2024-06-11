package database

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

fun getTasksDatabase(): RoomDatabase.Builder<TaskDatabase> {
    val dbFile = NSHomeDirectory() + "/tasks.db"

    return Room
        .databaseBuilder<TaskDatabase>(
            name = dbFile,
            factory = {
                TaskDatabase::class.instantiateImpl()
            }
        )
}