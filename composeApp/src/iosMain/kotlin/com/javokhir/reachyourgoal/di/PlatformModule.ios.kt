package com.javokhir.reachyourgoal.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.javokhir.reachyourgoal.database.ReachYourGoalDatabase
import com.javokhir.reachyourgoal.di.modules.createDataStore
import com.javokhir.reachyourgoal.di.modules.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<ReachYourGoalDatabase> { getDatabase() }
    single<DataStore<Preferences>> { createDataStore() }
}