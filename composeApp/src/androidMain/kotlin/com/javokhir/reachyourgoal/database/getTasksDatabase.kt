package com.javokhir.reachyourgoal.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import database.TaskDatabase

fun getTasksDatabase(context: Context): RoomDatabase.Builder<TaskDatabase> {
    val dbFile = context.getDatabasePath("tasks.db")
    return Room
        .databaseBuilder<TaskDatabase>(
            context = context,
            name = dbFile.absolutePath
        )
}