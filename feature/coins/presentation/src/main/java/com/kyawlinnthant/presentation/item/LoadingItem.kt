package com.kyawlinnthant.presentation.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.kyawlinnthant.presentation.preview.FullScreenProvider
import com.kyawlinnthant.theme.LineManWongNaiTheme
import com.kyawlinnthant.theme.dimen

@Composable
fun LoadingItem(
    modifier: Modifier = Modifier,
    enabledFullScreen: Boolean = false,
) {
    Box(
        modifier =
            modifier
                .then(
                    if (enabledFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth(),
                )
                .padding(MaterialTheme.dimen.base),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@PreviewScreenSizes
@Composable
private fun Preview(
    @PreviewParameter(FullScreenProvider::class) enabled: Boolean,
) {
    LineManWongNaiTheme {
        LoadingItem(enabledFullScreen = enabled)
    }
}
