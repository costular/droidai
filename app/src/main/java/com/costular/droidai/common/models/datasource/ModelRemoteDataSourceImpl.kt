package com.costular.droidai.common.models.datasource

import com.costular.droidai.core.network.Dispatcher
import com.costular.droidai.core.network.DroidAiDispatchers.*
import com.costular.droidai.common.models.model.ModelResponseDto
import com.costular.droidai.util.OllamaServer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.Url
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ModelRemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : ModelRemoteDataSource {
    override suspend fun getModels(): ModelResponseDto {
        return withContext(ioDispatcher) {
            httpClient.get(Url("${OllamaServer.Url}/api/tags")) {
                contentType(ContentType.Application.Json)
            }.body<ModelResponseDto>()
        }
    }
}
