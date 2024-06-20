package com.javokhir.reachyourgoal.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.javokhir.reachyourgoal.database.ReachYourGoalDatabase
import com.javokhir.reachyourgoal.di.modules.createDataStore
import com.javokhir.reachyourgoal.di.modules.getDatabase
import org.koin.dsl.module

actual val platformModule = module {
    single<ReachYourGoalDatabase> { getDatabase(get()) }
    single<DataStore<Preferences>> { createDataStore(get()) }
}