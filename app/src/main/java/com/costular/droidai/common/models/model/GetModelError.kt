package com.costular.droidai.common.models.model

sealed interface GetModelError {
    data object ConnectionError : GetModelError
    data object UnknownError : GetModelError
}
