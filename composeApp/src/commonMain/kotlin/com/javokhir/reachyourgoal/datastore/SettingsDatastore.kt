package com.javokhir.reachyourgoal.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.javokhir.reachyourgoal.locale.Language
import com.javokhir.reachyourgoal.locale.LocaleStrings
import com.javokhir.reachyourgoal.locale.languages.EnglishLocale
import com.javokhir.reachyourgoal.locale.languages.UzbekLocale
import com.javokhir.reachyourgoal.theme.ThemeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsDatastore(
    private val dataStore: DataStore<Preferences>
) {

    fun getCurrentTheme(): Flow<ThemeType> {
        return dataStore.data.map { preferences ->
            val themeTypeName = preferences[THEME_TYPE]

            if (themeTypeName != null) ThemeType.valueOf(themeTypeName) else ThemeType.SYSTEM_DEFAULT
        }
    }

    suspend fun setCurrentTheme(themeType: ThemeType) {
        dataStore.edit { preferences ->
            preferences[THEME_TYPE] = themeType.name
        }
    }

    suspend fun setLanguage(language: Language) {
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

    fun getLanguage(): Flow<Language> {
        return dataStore.data.map { preferences ->
            val language = preferences[APP_LOCALE]
                ?.let { Language.valueOf(it) }
                ?: Language.ENGLISH

            language
        }
    }

    private companion object {
        val THEME_TYPE = stringPreferencesKey("theme_type")
        val APP_LOCALE = stringPreferencesKey("app_locale")
    }
}