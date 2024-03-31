package com.costular.droidai.features.chat.api

import com.costular.droidai.core.network.Dispatcher
import com.costular.droidai.core.network.DroidAiDispatchers.IO
import com.costular.droidai.features.chat.model.ChatRequestDto
import com.costular.droidai.features.chat.model.ChatResponseDto
import com.costular.droidai.features.chat.model.MessageDto
import com.costular.droidai.features.settings.repository.SettingsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Url
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class ChatApiImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val settingsRepository: SettingsRepository,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : ChatApi {
    override suspend fun chat(
        model: String,
        messages: List<MessageDto>,
        stream: Boolean,
    ): ChatResponseDto {
        return withContext(ioDispatcher) {
            val url = settingsRepository.getOllamaUrl().first()
                ?: throw IllegalStateException("URL is null")

            httpClient.post(Url("${url.url}/api/chat")) {
                contentType(ContentType.Application.Json)
                setBody(
                    ChatRequestDto(model = model, messages = messages, stream = stream)
                )
            }.body<ChatResponseDto>()
        }
    }
}
