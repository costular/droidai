package com.costular.droidai.features.settings.ui

import com.costular.droidai.features.settings.model.OllamaUrl

sealed interface SettingsUiState {
    data object Loading : SettingsUiState

    data object Failure : SettingsUiState

    data class Success(
        val ollamaUrl: OllamaUrl? = null,
    ) : SettingsUiState
}
