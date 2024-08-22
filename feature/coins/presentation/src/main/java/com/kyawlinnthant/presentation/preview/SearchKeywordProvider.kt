package com.kyawlinnthant.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class SearchKeywordProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() =
            sequenceOf(
                "",
                "LineMan",
            )
}
