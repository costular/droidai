package com.costular.droidai.features.chat.ui

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.costular.droidai.common.models.model.GetModelError
import com.costular.droidai.common.models.model.Model
import com.costular.droidai.common.models.repository.ModelRepository
import com.costular.droidai.features.chat.model.Message
import com.costular.droidai.features.chat.model.MessageRole
import com.costular.droidai.features.chat.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val modelRepository: ModelRepository,
) : ViewModel() {

    private var chatGeneration: Job? = null

    private val _uiState: MutableStateFlow<ChatUIState> = MutableStateFlow(ChatUIState())
    val uiState = _uiState.asStateFlow()

    fun loadModels() {
        viewModelScope.launch {
            modelRepository.getModels().fold(
                ifLeft = { error ->
                    when (error) {
                        is GetModelError.ConnectionError -> {
                            _uiState.update { it.copy(isErrorConnection = true) }
                        }

                        is GetModelError.UnknownError -> Unit // TODO:
                    }
                },
                ifRight = { response ->
                    _uiState.update {
                        it.copy(
                            availableModels = response,
                            selectedModel = modelRepository.getDefaultModel(),
                            isErrorConnection = false,
                        )
                    }
                }
            )
        }
    }

    fun openModelPicker() {
        _uiState.update { it.copy(showModelPicker = true) }
    }


    fun onInput(value: TextFieldValue) {
        _uiState.update { it.copy(userInput = value) }
    }

    fun onSubmit() {
        sendChat(uiState.value.userInput.text)
    }

    fun onStopGenerating() {
        chatGeneration?.cancel()
        _uiState.update { it.copy(isGenerating = false) }
    }

    private fun sendChat(message: String) {
        onStopGenerating()
        chatGeneration = viewModelScope.launch {
            val messagesWithLatestPrompt = _uiState.value.messages + Message(
                role = MessageRole.User,
                content = message,
            )
            _uiState.update {
                it.copy(
                    messages = messagesWithLatestPrompt,
                    isGenerating = true,
                    userInput = TextFieldValue(""),
                )
            }

            val selectedModel = requireNotNull(uiState.value.selectedModel)

            val response = chatRepository.chat(
                model = selectedModel.name,
                messages = messagesWithLatestPrompt,
            )

            _uiState.update {
                it.copy(
                    messages = messagesWithLatestPrompt + response.message,
                    isGenerating = false,
                )
            }
        }
    }

    fun dismissModelPicker() {
        _uiState.update { it.copy(showModelPicker = false) }
    }

    fun onPickModel(model: Model) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selectedModel = model,
                    showModelPicker = false
                )
            }

            modelRepository.setDefaultModel(model)
        }
    }
}
