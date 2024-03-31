package com.costular.droidai.features.settings.repository

import kotlinx.coroutines.flow.Flow

interface SettingsLocalDataSource {
    fun getOllamaUrl(): Flow<String?>
    suspend fun setOllamaUrl(url: String)
}
