package com.costular.droidai.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@InstallIn(SingletonComponent::class)
@Module
interface NetworkModule {
    companion object {
        @Provides
        fun providesJson(): Json = Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }

        @Provides
        fun providesHttpClient(
            json: Json,
        ): HttpClient = HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(json)
            }
        }
    }

}
