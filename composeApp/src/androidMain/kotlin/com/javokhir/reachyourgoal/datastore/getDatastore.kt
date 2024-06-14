package com.javokhir.reachyourgoal.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import di.datastore.dataStoreFileName

fun createDataStore(context: Context): DataStore<Preferences> = di.datastore.createDataStore(
    producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
)