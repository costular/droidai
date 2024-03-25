package com.costular.droidai.common.models.repository

import arrow.core.Either
import com.costular.droidai.common.models.datasource.ModelLocalDataSource
import com.costular.droidai.common.models.datasource.ModelRemoteDataSource
import com.costular.droidai.common.models.model.GetModelError
import com.costular.droidai.common.models.model.Model
import com.costular.droidai.common.models.model.toDomain
import java.net.ConnectException
import javax.inject.Inject

class ModelRepositoryImpl @Inject constructor(
    private val modelRemoteDataSource: ModelRemoteDataSource,
    private val modelLocalDataSource: ModelLocalDataSource,
) : ModelRepository {
    override suspend fun getModels(): Either<GetModelError, List<Model>> {
        return try {
            Either.Right(modelRemoteDataSource.getModels().models.map { it.toDomain() })
        } catch (connectException: ConnectException) {
            Either.Left(GetModelError.ConnectionError)
        } catch (e: Exception) {
            Either.Left(GetModelError.UnknownError)
        }
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
