package com.javokhir.reachyourgoal.di.modules

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.javokhir.reachyourgoal.database.ReachYourGoalDatabase

fun getDatabase(context: Context): ReachYourGoalDatabase {
    val dbFile = context.getDatabasePath("reach_your_goal.db")
    return Room
        .databaseBuilder<ReachYourGoalDatabase>(
            context = context,
            name = dbFile.absolutePath
        )
        .fallbackToDestructiveMigration(true)
        .setDriver(BundledSQLiteDriver())
        .build()
}