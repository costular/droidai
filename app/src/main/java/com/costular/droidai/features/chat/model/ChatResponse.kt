package com.costular.droidai.features.chat.model

import java.time.Instant

data class ChatResponse(
    val model: String,
    val createdAt: Instant,
    val message: Message,
    val done: Boolean,
)
