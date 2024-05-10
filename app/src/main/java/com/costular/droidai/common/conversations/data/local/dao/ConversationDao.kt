package com.costular.droidai.common.conversations.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.costular.droidai.common.conversations.data.local.entity.ConversationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {
    @Query("SELECT * FROM conversations ORDER BY updated_at")
    fun getConversations(): Flow<List<ConversationEntity>>

    @Insert
    suspend fun createConversation(conversationEntity: ConversationEntity): String

    @Query("DELETE FROM conversations WHERE id = :id")
    suspend fun deleteConversation(id: String)
}
