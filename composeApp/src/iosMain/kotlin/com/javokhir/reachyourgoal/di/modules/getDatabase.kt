package com.javokhir.reachyourgoal.di.modules

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.javokhir.reachyourgoal.database.ReachYourGoalDatabase
import com.javokhir.reachyourgoal.database.instantiateImpl
import platform.Foundation.NSHomeDirectory

fun getDatabase(): ReachYourGoalDatabase {
    val dbFile = NSHomeDirectory() + "/tasks.db"

    return Room
        .databaseBuilder<ReachYourGoalDatabase>(
            name = dbFile,
            factory = {
                ReachYourGoalDatabase::class.instantiateImpl()
            }
        )
        .fallbackToDestructiveMigration(true)
        .setDriver(BundledSQLiteDriver())
        .build()
}