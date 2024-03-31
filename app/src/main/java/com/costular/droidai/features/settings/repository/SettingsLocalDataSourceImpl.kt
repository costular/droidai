package com.costular.droidai.features.settings.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SettingsLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : SettingsLocalDataSource {

    private val ollamaUrl = stringPreferencesKey(PREF_OLLAMA_URL)

    override fun getOllamaUrl(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[ollamaUrl]
        }
    }

    override suspend fun setOllamaUrl(url: String) {
        dataStore.edit { preferences ->
            preferences[ollamaUrl] = url
        }
    }

    private companion object {
        const val PREF_OLLAMA_URL = "ollama_url"
    }
}
