package com.costular.droidai.features.chat.model

import androidx.compose.ui.text.toLowerCase

fun MessageDto.toDomain(): Message = Message(
    role = when (role) {
        "assistant" -> MessageRole.Assistant

        "user" -> MessageRole.User

        "system" -> MessageRole.System
        else -> throw IllegalArgumentException("$role role not expected")
    },
    content = this.content,
)

fun Message.toDto(): MessageDto = MessageDto(
    role = role.toString().lowercase(),
    content = content
)

fun ChatResponseDto.toDomain(): ChatResponse = ChatResponse(
    model = model,
    createdAt = createdAt,
    message = message.toDomain(),
    done = done,
)
