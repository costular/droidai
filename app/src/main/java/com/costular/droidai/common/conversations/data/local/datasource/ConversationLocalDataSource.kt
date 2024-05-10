package com.costular.droidai.common.conversations.data.local.datasource

import com.costular.droidai.common.conversations.data.local.entity.ConversationEntity
import kotlinx.coroutines.flow.Flow

interface ConversationLocalDataSource {
    fun getConversations(): Flow<List<ConversationEntity>>
    suspend fun createConversation(model: String): String
    suspend fun deleteConversation(id: String)
}
