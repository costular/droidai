package com.costular.droidai.common.conversations.data.local.datasource

import com.costular.droidai.common.conversations.data.local.dao.ConversationDao
import com.costular.droidai.common.conversations.data.local.entity.ConversationEntity
import com.costular.droidai.common.conversations.data.local.mapper.toConversation
import com.costular.droidai.common.conversations.domain.model.Conversation
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConversationLocalDataSourceImpl @Inject constructor(
    private val conversationDao: ConversationDao,
) : ConversationLocalDataSource {
    override fun getConversations(): Flow<List<ConversationEntity>> =
        conversationDao.getConversations()

    override suspend fun createConversation(model: String): String {
        val conversation = ConversationEntity(
            id = UUID.randomUUID().toString(),
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now(),
            model = model,
            messages = emptyList(),
        )
        return conversationDao.createConversation(conversation)
    }

    override suspend fun deleteConversation(id: String) {
        conversationDao.deleteConversation(id)
    }
}
