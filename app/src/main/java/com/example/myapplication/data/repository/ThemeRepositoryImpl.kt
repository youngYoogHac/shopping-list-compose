package com.example.myapplication.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : ThemeRepository {

    private companion object {

        val DARK_THEME_KEY =
            booleanPreferencesKey(
                "dark_theme"
            )
    }

    override val isDarkTheme: Flow<Boolean> =
        dataStore.data.map { prefs ->

            prefs[DARK_THEME_KEY] ?: false
        }

    override suspend fun setDarkTheme(
        enabled: Boolean
    ) {

        dataStore.edit { prefs ->

            prefs[DARK_THEME_KEY] =
                enabled
        }
    }
}