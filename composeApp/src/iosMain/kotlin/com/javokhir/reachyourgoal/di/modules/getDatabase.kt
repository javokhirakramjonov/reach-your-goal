package com.javokhir.reachyourgoal.di.modules

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.javokhir.reachyourgoal.database.ReachYourGoalDatabase
import com.javokhir.reachyourgoal.database.instantiateImpl
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask

fun getDatabase(): ReachYourGoalDatabase {
    val dbPath = NSSearchPathForDirectoriesInDomains(
        NSDocumentDirectory,
        NSUserDomainMask,
        true
    ).first() as String

    val fullPath = "$dbPath/reach_your_goal.db"

    return Room
        .databaseBuilder<ReachYourGoalDatabase>(
            name = fullPath,
            factory = {
                ReachYourGoalDatabase::class.instantiateImpl()
            }
        )
        .fallbackToDestructiveMigration(true)
        .setDriver(BundledSQLiteDriver())
        .build()
}