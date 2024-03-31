package com.costular.droidai.features.settings.repository

import com.costular.droidai.features.settings.model.OllamaUrl
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl @Inject constructor(
    private val localDataSource: SettingsLocalDataSource,
): SettingsRepository {
    override fun getOllamaUrl(): Flow<OllamaUrl?> {
        return localDataSource.getOllamaUrl().map { it?.let { OllamaUrl(it) } }
    }

    override suspend fun setOllamaUrl(url: String) {
        localDataSource.setOllamaUrl(url)
    }
}
