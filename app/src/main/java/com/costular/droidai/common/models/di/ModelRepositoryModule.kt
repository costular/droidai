package com.costular.droidai.common.models.di

import com.costular.droidai.common.models.repository.ModelRepository
import com.costular.droidai.common.models.repository.ModelRepositoryImpl
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
