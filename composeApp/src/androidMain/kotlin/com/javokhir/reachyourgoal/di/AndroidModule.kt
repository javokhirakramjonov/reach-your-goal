package com.javokhir.reachyourgoal.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import com.javokhir.reachyourgoal.database.getDatabase
import com.javokhir.reachyourgoal.datastore.createDataStore
import database.ReachYourGoalDatabase
import org.koin.dsl.module

fun androidModules() = listOf(
    databaseModule,
    datastoreModule
)

private val databaseModule = module {
    single<RoomDatabase.Builder<ReachYourGoalDatabase>> { getDatabase(get()) }
}

private val datastoreModule = module {
    single<DataStore<Preferences>> { createDataStore(get()) }
}