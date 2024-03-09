package com.costular.droidai.features.chat.di

import com.costular.droidai.features.chat.api.ChatApi
import com.costular.droidai.features.chat.api.ChatApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface ChatApiModule {
    @Binds
    fun bindsChatApi(
        chatApiImpl: ChatApiImpl
    ): ChatApi
}
