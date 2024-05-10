package com.costular.droidai.common.conversations.domain.repository

import com.costular.droidai.common.conversations.domain.model.Conversation
import com.costular.droidai.common.conversations.domain.model.ConversationId
import kotlinx.coroutines.flow.Flow

interface ConversationRepository {
    fun getConversations(): Flow<List<Conversation>>
    suspend fun createConversation(): ConversationId
    suspend fun deleteConversation(id: String)
    suspend fun sendMessage(conversationId: ConversationId, message: String)
}
