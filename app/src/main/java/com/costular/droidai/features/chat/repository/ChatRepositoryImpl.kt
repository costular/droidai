package com.costular.droidai.features.chat.repository

import com.costular.droidai.features.chat.api.ChatApi
import com.costular.droidai.features.chat.model.ChatResponse
import com.costular.droidai.features.chat.model.Message
import com.costular.droidai.features.chat.model.toDomain
import com.costular.droidai.features.chat.model.toDto
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatApi: ChatApi
) : ChatRepository {
    override suspend fun chat(model: String, messages: List<Message>): ChatResponse {
        return chatApi.chat(
            model = model,
            messages = messages.map { it.toDto() },
            stream = false,
        ).toDomain()
    }
}
