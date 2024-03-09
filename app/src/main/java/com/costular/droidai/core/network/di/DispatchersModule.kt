package com.costular.droidai.core.network.di

import com.costular.droidai.core.network.Dispatcher
import com.costular.droidai.core.network.DroidAiDispatchers
import com.costular.droidai.core.network.DroidAiDispatchers.Default
import com.costular.droidai.core.network.DroidAiDispatchers.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
