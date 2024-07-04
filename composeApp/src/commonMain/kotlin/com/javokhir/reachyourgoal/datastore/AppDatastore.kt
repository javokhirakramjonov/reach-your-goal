package com.javokhir.reachyourgoal.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

class AppDatastore(
    private val dataStore: DataStore<Preferences>
) {
}