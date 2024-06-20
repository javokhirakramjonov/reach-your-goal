package com.javokhir.reachyourgoal.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

fun createDataStore(context: Context): DataStore<Preferences> {
    val path = context.filesDir.resolve("ryg.preferences_pb").absolutePath.toPath()

    return PreferenceDataStoreFactory.createWithPath(produceFile = { path })
}