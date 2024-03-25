package com.costular.droidai.common.models.repository

import arrow.core.Either
import com.costular.droidai.common.models.model.GetModelError
import com.costular.droidai.common.models.model.Model

interface ModelRepository {
    suspend fun getModels(): Either<GetModelError, List<Model>>
    suspend fun getDefaultModel(): Model
    suspend fun setDefaultModel(model: Model)
}
