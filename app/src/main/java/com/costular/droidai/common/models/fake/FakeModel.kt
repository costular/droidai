package com.costular.droidai.common.models.fake

import com.costular.droidai.common.models.model.Model
import java.time.Instant

val ModelLlama2 = Model(
    name = "Llama2:latest",
    modifiedAt = Instant.now(),
    size = 3826793677,
    digest = "78e26419b4469263f75331927a00a0284ef6544c1975b826b15abdaef17bb962",
    parameterSize = "7B",
    quantizationLevel = "Q4_0",
)
val ModelMistral = Model(
    name = "mistral:latest",
    modifiedAt = Instant.now(),
    size = 4109865159,
    digest = "61e88e884507ba5e06c49b40e6226884b2a16e872382c2b44a42f2d119d804a5",
    parameterSize = "7B",
    quantizationLevel = "Q4_0",
)
