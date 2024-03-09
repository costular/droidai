package com.costular.droidai.features.chat.model

import java.time.Instant

data class Model(
    val name: String,
    val modifiedAt: Instant,
    val size: Long,
    val digest: String,
    val parameterSize: String,
    val quantizationLevel: String,
)
