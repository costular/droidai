package com.costular.droidai.features.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.costular.droidai.features.settings.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    val uiState: StateFlow<SettingsUiState> = settingsRepository.getOllamaUrl()
        .map { ollamaUrl ->
            SettingsUiState.Success(
                ollamaUrl = ollamaUrl,
            )
        }
        .catch {
            it.printStackTrace()
            SettingsUiState.Failure
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SettingsUiState.Loading,
        )

    private val _showEditOllamaUrl: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showEditOllamaUrl = _showEditOllamaUrl.asStateFlow()

    fun updateUrl(ollamaUrl: String) {
        viewModelScope.launch {
            settingsRepository.setOllamaUrl(ollamaUrl)
        }
    }

    fun onEditOllamaUrl() {
        _showEditOllamaUrl.update { true }
    }

    fun updateOllamaUrl(url: String) {
        viewModelScope.launch {
            dismissEditOllamaUrl()
            settingsRepository.setOllamaUrl(url)
        }
    }

    fun dismissEditOllamaUrl() {
        _showEditOllamaUrl.update { false }
    }
}
