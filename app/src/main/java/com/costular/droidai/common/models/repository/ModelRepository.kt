package com.costular.droidai.common.models.repository

import com.costular.droidai.common.models.model.Model

interface ModelRepository {
    suspend fun getModels(): List<Model>
    suspend fun getDefaultModel(): Model
    suspend fun setDefaultModel(model: Model)
}
