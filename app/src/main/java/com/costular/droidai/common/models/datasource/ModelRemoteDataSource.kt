package com.costular.droidai.common.models.datasource

import com.costular.droidai.common.models.model.ModelResponseDto

interface ModelRemoteDataSource {
    suspend fun getModels(): ModelResponseDto
}
