package com.costular.droidai.common.conversations.domain.model

import com.costular.droidai.features.chat.model.Message
import java.time.LocalDate

data class Conversation(
    val id: ConversationId,
    val createdAt: LocalDate,
    val updatedAt: LocalDate,
    val model: String,
    val messages: List<Message>,
)
