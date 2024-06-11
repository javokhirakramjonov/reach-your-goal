package com.javokhir.reachyourgoal.di

import androidx.room.RoomDatabase
import com.javokhir.reachyourgoal.database.getTasksDatabase
import database.TaskDatabase
import org.koin.dsl.module

fun androidModules() = listOf(
    databaseModule
)

private val databaseModule = module {
    single<RoomDatabase.Builder<TaskDatabase>> {
        getTasksDatabase(get())
    }
}