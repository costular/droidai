package com.costular.droidai.common.models.model

import android.text.format.DateUtils
import com.costular.droidai.util.toReadableFileSize
import java.time.Instant

data class Model(
    val name: String,
    val modifiedAt: Instant,
    val size: Long,
    val digest: String,
    val parameterSize: String,
    val quantizationLevel: String,
) {
    val id: String get() = digest.substring(0, 12)

    val sizeHumanReadable: String get() = size.toReadableFileSize()

    val editedHumanReadable: String get() =
        DateUtils.getRelativeTimeSpanString(
            modifiedAt.toEpochMilli(),
            Instant.now().toEpochMilli(), // TODO: Replace with date provider for testing purposes
            DateUtils.MINUTE_IN_MILLIS
        ).toString()
}
