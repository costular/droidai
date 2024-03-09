package com.costular.droidai.features.chat.api

import com.costular.droidai.features.chat.model.ModelResponseDto

interface ModelApi {
    suspend fun getModels(): ModelResponseDto
}
