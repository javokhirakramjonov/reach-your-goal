package datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import theme.ThemeType

class SettingsDatastore(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val THEME_TYPE = stringPreferencesKey("theme_type")
    }

    fun getCurrentTheme() : Flow<ThemeType>  {
        return dataStore.data.map { preferences ->
            val themeTypeName = preferences[THEME_TYPE]

            if(themeTypeName != null) ThemeType.valueOf(themeTypeName) else ThemeType.SYSTEM_DEFAULT
        }
    }

    suspend fun setCurrentTheme(themeType: ThemeType) {
        dataStore.edit { preferences ->
            preferences[THEME_TYPE] = themeType.name
        }
    }
}