package com.costular.droidai.common.models.datasource

import com.costular.droidai.common.models.model.ModelResponseDto
import com.costular.droidai.core.network.Dispatcher
import com.costular.droidai.core.network.DroidAiDispatchers.IO
import com.costular.droidai.features.settings.repository.SettingsRepository
import com.costular.droidai.util.OllamaServer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.Url
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class ModelRemoteDataSourceImpl @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val httpClient: HttpClient,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : ModelRemoteDataSource {
    override suspend fun getModels(): ModelResponseDto {
        return withContext(ioDispatcher) {
            val url = settingsRepository.getOllamaUrl().first()
                ?: throw IllegalStateException("URL cannot be null")

            httpClient.get(Url("${url.url}/api/tags")) {
                contentType(ContentType.Application.Json)
            }.body<ModelResponseDto>()
        }
    }
}
