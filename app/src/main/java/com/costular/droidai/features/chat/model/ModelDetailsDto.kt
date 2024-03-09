package com.costular.droidai.features.chat.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModelDetailsDto(
    @SerialName("format") val format: String,
    @SerialName("family") val family: String,
    @SerialName("parameter_size") val parameterSize: String,
    @SerialName("quantization_level") val quantizationLevel: String,
)
