package com.costular.droidai.common.models.model

import com.costular.droidai.core.network.InstantAsText
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModelDto(
    @SerialName("name") val name: String,
    @SerialName("size") val size: Long,
    @SerialName("modified_at") val modifiedAt: InstantAsText,
    @SerialName("digest") val digest: String,
    @SerialName("details") val details: ModelDetailsDto
)
