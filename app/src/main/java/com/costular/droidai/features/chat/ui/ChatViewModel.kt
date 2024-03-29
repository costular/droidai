package com.costular.droidai.features.chat.ui

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.costular.droidai.features.chat.model.Message
import com.costular.droidai.features.chat.model.MessageRole
import com.costular.droidai.features.chat.repository.ChatRepository
import com.costular.droidai.features.chat.repository.ModelRepository
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

    init {
        loadModels()
    }

    fun loadModels() {
        viewModelScope.launch {
            val response = modelRepository.getModels()
            _uiState.update {
                it.copy(
                    availableModels = response,
                    selectedModel = response.first()
                ) // TODO: let user choose default model in settings but put a fallback when there's no one
            }
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

    fun sendChat(message: String) {
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

            val response = chatRepository.chat(
                model = "llama2",
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
}
