package com.costular.droidai.features.settings.di

import com.costular.droidai.features.settings.repository.SettingsLocalDataSource
import com.costular.droidai.features.settings.repository.SettingsLocalDataSourceImpl
import com.costular.droidai.features.settings.repository.SettingsRepository
import com.costular.droidai.features.settings.repository.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface SettingsRepositoryModule {
    @Binds
    fun bindsRepository(
        settingsRepositoryImpl: SettingsRepositoryImpl
    ): SettingsRepository

    @Binds
    fun bindLocalDataSource(
        settingsLocalDataSourceImpl: SettingsLocalDataSourceImpl
    ): SettingsLocalDataSource
}
