package com.javokhir.reachyourgoal.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.javokhir.reachyourgoal.database.getDatabase
import com.javokhir.reachyourgoal.datastore.createDataStore
import org.koin.dsl.module

fun iosModules() = listOf(
    databaseModule,
    datastoreModule
)

private val databaseModule = module {
    single {
        getDatabase()
    }
}

private val datastoreModule = module {
    single<DataStore<Preferences>> { createDataStore() }
}