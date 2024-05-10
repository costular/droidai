package com.costular.droidai.common.conversations.data.local.mapper

import com.costular.droidai.common.conversations.data.local.entity.ConversationEntity
import com.costular.droidai.common.conversations.domain.model.Conversation
import com.costular.droidai.common.conversations.domain.model.ConversationId

internal fun ConversationEntity.toConversation(): Conversation = Conversation(
    id = ConversationId(id),
    createdAt = createdAt,
    updatedAt = updatedAt,
    model = model,
    messages = messages.map { it.toMessage() },
)
