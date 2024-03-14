package com.costular.droidai.common.models.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ModelLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : ModelLocalDataSource {

    private val defaultModel = stringPreferencesKey(PREF_DEFAULT_MODEL)

    override suspend fun getDefaultModel(): String? {
        return dataStore.data.map { preferences ->
            preferences[defaultModel]
        }.first()
    }

    override suspend fun setDefaultModel(model: String) {
        dataStore.edit { settings ->
            settings[defaultModel] = model
        }
    }

    private companion object {
        const val PREF_DEFAULT_MODEL = "default_model"
    }
}
