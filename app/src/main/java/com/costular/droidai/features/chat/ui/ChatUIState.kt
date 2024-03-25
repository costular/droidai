package com.costular.droidai.features.chat.ui

import androidx.compose.ui.text.input.TextFieldValue
import com.costular.droidai.features.chat.model.Message
import com.costular.droidai.common.models.model.Model

data class ChatUIState(
    val messages: List<Message> = emptyList(),
    val isGenerating: Boolean = false,
    val userInput: TextFieldValue = TextFieldValue(""),
    val selectedModel: Model? = null,
    val availableModels: List<Model> = emptyList(),
    val showModelPicker: Boolean = false,
    val isErrorConnection: Boolean = false,
)
