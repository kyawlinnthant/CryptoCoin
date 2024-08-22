package com.kyawlinnthant.presentation.item

import androidx.compose.ui.graphics.Color

fun String.toColor(): Color? {
    return try {
        Color(android.graphics.Color.parseColor(this))
    } catch (e: IllegalArgumentException) {
        null
    }
}
