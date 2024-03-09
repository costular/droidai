package com.costular.droidai.features.chat.repository

import com.costular.droidai.features.chat.model.ChatResponse
import com.costular.droidai.features.chat.model.Message

interface ChatRepository {
    suspend fun chat(
        model: String,
        messages: List<Message>,
    ): ChatResponse
}
