package com.javokhir.reachyourgoal.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import database.ReachYourGoalDatabase

fun getDatabase(context: Context): RoomDatabase.Builder<ReachYourGoalDatabase> {
    val dbFile = context.getDatabasePath("reach_your_goal.db")
    return Room
        .databaseBuilder<ReachYourGoalDatabase>(
            context = context,
            name = dbFile.absolutePath
        )
}