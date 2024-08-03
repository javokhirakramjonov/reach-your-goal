package com.javokhir.reachyourgoal.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.javokhir.reachyourgoal.locale.Language
import com.javokhir.reachyourgoal.locale.LocaleStrings
import com.javokhir.reachyourgoal.locale.languages.EnglishLocale
import com.javokhir.reachyourgoal.locale.languages.UzbekLocale
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDatastore(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun setAppLocale(language: Language) {
        dataStore.edit { preferences ->
            preferences[APP_LOCALE] = language.name
        }
    }

    fun getAppLocale(): Flow<LocaleStrings> {
        return dataStore.data.map { preferences ->
            val language = preferences[APP_LOCALE]
                ?.let { Language.valueOf(it) }
                ?: Language.ENGLISH

            when (language) {
                Language.ENGLISH -> EnglishLocale
                Language.UZBEK -> UzbekLocale
            }
        }
    }

    private companion object {
        val APP_LOCALE = stringPreferencesKey("app_locale")
    }

}