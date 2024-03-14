package com.costular.droidai.common.models.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModelResponseDto(
    @SerialName("models") val models: List<ModelDto>
)
