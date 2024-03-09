package com.costular.droidai.features.chat.di

import com.costular.droidai.features.chat.repository.ModelRepository
import com.costular.droidai.features.chat.repository.ModelRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface ModelRepositoryModule {
    @Binds
    fun bindsModelRepository(modelRepositoryImpl: ModelRepositoryImpl): ModelRepository
}
