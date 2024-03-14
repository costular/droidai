package com.costular.droidai.core.network

import javax.inject.Qualifier

@Target(
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.TYPE
)
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val droidAiDispatcher: DroidAiDispatchers)

enum class DroidAiDispatchers {
    Default,
    IO,
}
