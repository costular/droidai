package com.costular.droidai.core.network

import android.util.Log
import io.ktor.client.plugins.logging.Logger

class HttpLogger : Logger {
    override fun log(message: String) {
        Log.d(TAG, message)
    }

    private companion object {
        const val TAG = "DroidAI"
    }
}
