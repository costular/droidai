package com.costular.droidai.common.conversations.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Relation
import java.time.LocalDate

@Entity(
    tableName = "conversations",
)
data class ConversationEntity(
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "created_at") val createdAt: LocalDate,
    @ColumnInfo(name = "updated_at") val updatedAt: LocalDate,
    @ColumnInfo(name = "model") val model: String,
    @Relation(
        entity = MessageEntity::class,
        parentColumn = "id",
        entityColumn = "conversationId",
    )
    val messages: List<MessageEntity>
)
