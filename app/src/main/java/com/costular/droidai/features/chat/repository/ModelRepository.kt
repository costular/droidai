package com.costular.droidai.features.chat.repository

import com.costular.droidai.features.chat.model.Model

interface ModelRepository {
    suspend fun getModels(): List<Model>
}
