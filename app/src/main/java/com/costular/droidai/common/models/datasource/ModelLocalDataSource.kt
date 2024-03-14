package com.costular.droidai.common.models.datasource

import com.costular.droidai.common.models.model.Model

interface ModelLocalDataSource {
    suspend fun getDefaultModel(): String?
    suspend fun setDefaultModel(model: String)
}
