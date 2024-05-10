package com.costular.droidai.common.conversations.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "messages",
)
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "role") val role: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "conversationId") val conversationId: String,
)
