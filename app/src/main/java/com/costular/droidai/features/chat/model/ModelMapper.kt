package com.costular.droidai.features.chat.model

fun ModelDto.toDomain(): Model = Model(
    name = name,
    modifiedAt = modifiedAt,
    size = size,
    digest = digest,
    parameterSize = details.parameterSize,
    quantizationLevel = details.quantizationLevel,
)
