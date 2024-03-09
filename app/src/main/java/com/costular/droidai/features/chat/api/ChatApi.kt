package com.costular.droidai.features.chat.api

import com.costular.droidai.features.chat.model.ChatResponseDto
import com.costular.droidai.features.chat.model.MessageDto

interface ChatApi {
    suspend fun chat(
        model: String,
        messages: List<MessageDto>,
        stream: Boolean,
    ): ChatResponseDto
}
