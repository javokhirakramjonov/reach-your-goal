package com.javokhir.reachyourgoal.di.modules

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask

fun createDataStore(): DataStore<Preferences> {
    val documentPath = NSSearchPathForDirectoriesInDomains(
        NSDocumentDirectory,
        NSUserDomainMask,
        true
    ).first() as String

    val fullPath = "$documentPath/reach_your_goal.preferences_pb"

    return PreferenceDataStoreFactory.createWithPath(produceFile = { fullPath.toPath() })
}