package com.costular.droidai.features.settings.model

import android.webkit.URLUtil

@JvmInline
value class OllamaUrl(val url: String) {
    val isValid: Boolean get() = URLUtil.isValidUrl(url)
}
