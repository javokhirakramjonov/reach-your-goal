package com.javokhir.reachyourgoal.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class AppDatastore(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val CURRENT_PLAN_ID = intPreferencesKey("current_plan_id")
        private val CURRENT_SCHEDULE_ID: (Int) -> Preferences.Key<Int> =
            { intPreferencesKey("current_schedule_id/$it") }
    }

    fun getCurrentPlan(): Flow<Int?> {
        return dataStore
            .data
            .map { preferences ->
                preferences[CURRENT_PLAN_ID]
            }
    }

    suspend fun setCurrentPlan(planId: Int) {
        dataStore.edit { preferences ->
            preferences[CURRENT_PLAN_ID] = planId
        }
    }

    suspend fun getCurrentScheduleId(planId: Int): Int? {
        return dataStore
            .data
            .map { preferences ->
                preferences[CURRENT_SCHEDULE_ID(planId)]
            }
            .firstOrNull()
    }

    suspend fun setCurrentScheduleId(planId: Int, scheduleId: Int) {
        dataStore.edit { preferences ->
            preferences[CURRENT_SCHEDULE_ID(planId)] = scheduleId
        }
    }
}