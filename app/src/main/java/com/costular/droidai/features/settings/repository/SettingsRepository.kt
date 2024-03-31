package com.costular.droidai.features.settings.repository

import com.costular.droidai.features.settings.model.OllamaUrl
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getOllamaUrl(): Flow<OllamaUrl?>
    suspend fun setOllamaUrl(url: String)
}
