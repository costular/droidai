package com.costular.droidai.features.chat.repository

import com.costular.droidai.features.chat.api.ModelApi
import com.costular.droidai.features.chat.model.Model
import com.costular.droidai.features.chat.model.toDomain
import javax.inject.Inject

class ModelRepositoryImpl @Inject constructor(
    private val modelApi: ModelApi,
) : ModelRepository {
    override suspend fun getModels(): List<Model> {
        return modelApi.getModels().models.map { it.toDomain() }
    }
}
