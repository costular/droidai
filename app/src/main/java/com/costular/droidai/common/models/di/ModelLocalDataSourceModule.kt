package com.costular.droidai.common.models.di

import com.costular.droidai.common.models.datasource.ModelLocalDataSource
import com.costular.droidai.common.models.datasource.ModelLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface ModelLocalDataSourceModule {
    @Binds
    fun bindsModelLocalDataSource(
        modelLocalDataSourceImpl: ModelLocalDataSourceImpl,
    ): ModelLocalDataSource
}
