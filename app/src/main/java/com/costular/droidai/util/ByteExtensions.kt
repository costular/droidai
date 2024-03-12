package com.costular.droidai.util

fun Long.toReadableFileSize(): String {
    if (this <= 0) return "0 B"

    val units = arrayOf("B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB")
    val digitGroups = (Math.log10(this.toDouble()) / Math.log10(1024.0)).toInt()

    return String.format(
        "%.2f %s",
        this / Math.pow(1024.0, digitGroups.toDouble()),
        units[digitGroups]
    )
}
