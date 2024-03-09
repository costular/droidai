package com.costular.droidai.core.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val droidAiDispatcher: DroidAiDispatchers)

enum class DroidAiDispatchers {
    Default,
    IO,
}
