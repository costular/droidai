package com.costular.droidai.common.conversations.data.local.mapper

import com.costular.droidai.common.conversations.data.local.entity.MessageEntity
import com.costular.droidai.features.chat.model.Message
import com.costular.droidai.features.chat.model.MessageRole


fun MessageEntity.toMessage(): Message = Message(
    role = MessageRole.entries.find { it.name == role }!!,
    content = content,
)
