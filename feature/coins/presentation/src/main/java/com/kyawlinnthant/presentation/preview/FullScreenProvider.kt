package com.kyawlinnthant.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class FullScreenProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() =
            sequenceOf(
                true,
                false,
            )
}
