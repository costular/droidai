package com.costular.droidai.features.chat.di

import com.costular.droidai.features.chat.repository.ChatRepository
import com.costular.droidai.features.chat.repository.ChatRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface ChatRepositoryModule {
    @Binds
    fun bindsRepository(chatRepositoryImpl: ChatRepositoryImpl): ChatRepository
}
