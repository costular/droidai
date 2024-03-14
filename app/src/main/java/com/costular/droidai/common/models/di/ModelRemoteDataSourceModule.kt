package com.costular.droidai.common.models.di

import com.costular.droidai.common.models.datasource.ModelRemoteDataSource
import com.costular.droidai.common.models.datasource.ModelRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface ModelRemoteDataSourceModule {
    @Binds
    fun bindsModelApi(modelApiImpl: ModelRemoteDataSourceImpl): ModelRemoteDataSource
}
