package com.costular.droidai.data

import com.costular.droidai.common.models.model.ModelDetailsDto
import com.costular.droidai.common.models.model.ModelDto
import java.time.Instant

val ModelDtoLlama2 = ModelDto(
    name = "Llama2:latest",
    modifiedAt = Instant.now(),
    size = 3826793677,
    digest = "78e26419b4469263f75331927a00a0284ef6544c1975b826b15abdaef17bb962",
    details = ModelDetailsDto(
        format = "",
        family = "llama",
        parameterSize = "7B",
        quantizationLevel = "Q4_0",
    ),
)
val ModelDtoMistral = ModelDto(
    name = "mistral:latest",
    modifiedAt = Instant.now(),
    size = 4109865159,
    digest = "61e88e884507ba5e06c49b40e6226884b2a16e872382c2b44a42f2d119d804a5",
    details = ModelDetailsDto(
        format = "",
        family = "mistral",
        parameterSize = "7B",
        quantizationLevel = "Q4_0",
    ),
)
