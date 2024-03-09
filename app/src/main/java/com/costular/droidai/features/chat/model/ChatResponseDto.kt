package com.costular.droidai.features.chat.model

import com.costular.droidai.network.InstantAsText
import java.time.Instant
import java.time.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatResponseDto(
    @SerialName("model") val model: String,
    @SerialName("created_at") val createdAt: InstantAsText,
    @SerialName("message") val message: MessageDto,
    @SerialName("done") val done: Boolean,
)
