package com.costular.droidai.features.chat.di

import com.costular.droidai.features.chat.api.ModelApi
import com.costular.droidai.features.chat.api.ModelApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface ModelApiModule {
    @Binds
    fun bindsModelApi(modelApiImpl: ModelApiImpl): ModelApi
}
