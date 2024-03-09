package com.costular.droidai.features.chat.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatRequestDto(
    val model: String,
    val messages: List<MessageDto>,
    val stream: Boolean,
)
