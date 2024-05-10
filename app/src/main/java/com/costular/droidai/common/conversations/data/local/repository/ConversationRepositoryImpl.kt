package com.costular.droidai.common.conversations.data.local.repository

import com.costular.droidai.common.conversations.data.local.datasource.ConversationLocalDataSource
import com.costular.droidai.common.conversations.data.local.mapper.toConversation
import com.costular.droidai.common.conversations.domain.model.Conversation
import com.costular.droidai.common.conversations.domain.model.ConversationId
import com.costular.droidai.common.conversations.domain.repository.ConversationRepository
import com.costular.droidai.common.models.repository.ModelRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConversationRepositoryImpl @Inject constructor(
    private val conversationLocalDataSource: ConversationLocalDataSource,
    private val modelRepository: ModelRepository,
) : ConversationRepository {
    override fun getConversations(): Flow<List<Conversation>> {
        return conversationLocalDataSource.getConversations().map { it.map { it.toConversation() } }
    }

    override suspend fun createConversation(): ConversationId {
        val defaultModel = modelRepository.getDefaultModel()
        val conversationId = conversationLocalDataSource.createConversation(defaultModel.name)
        return ConversationId(conversationId)
    }

    override suspend fun deleteConversation(id: String) {
        conversationLocalDataSource.deleteConversation(id)
    }
}
