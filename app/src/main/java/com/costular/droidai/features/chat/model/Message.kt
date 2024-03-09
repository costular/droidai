package com.costular.droidai.features.chat.model

data class Message(
    val role: MessageRole,
    val content: String,
)
