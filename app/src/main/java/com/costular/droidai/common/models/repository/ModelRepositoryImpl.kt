package com.costular.droidai.common.models.repository

import com.costular.droidai.common.models.datasource.ModelLocalDataSource
import com.costular.droidai.common.models.datasource.ModelRemoteDataSource
import com.costular.droidai.common.models.model.Model
import com.costular.droidai.common.models.model.toDomain
import javax.inject.Inject

class ModelRepositoryImpl @Inject constructor(
    private val modelRemoteDataSource: ModelRemoteDataSource,
    private val modelLocalDataSource: ModelLocalDataSource,
) : ModelRepository {
    override suspend fun getModels(): List<Model> {
        return modelRemoteDataSource.getModels().models.map { it.toDomain() }
    }

    override suspend fun getDefaultModel(): Model {
        return modelRemoteDataSource.getModels()
            .models
            .map { it.toDomain() }
            .findPreferredModel()
    }

    override suspend fun setDefaultModel(model: Model) {
        modelLocalDataSource.setDefaultModel(model.name)
    }

    private suspend fun List<Model>.findPreferredModel(): Model {
        val defaultModel = modelLocalDataSource.getDefaultModel()

        return if (defaultModel != null) {
            val found = find { it.name == defaultModel }
            found ?: first()
        } else {
            first()
        }
    }
}
