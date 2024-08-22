package com.kyawlinnthant.presentation.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.kyawlinnthant.coins.presentation.R
import com.kyawlinnthant.presentation.preview.FullScreenProvider
import com.kyawlinnthant.theme.LineManWongNaiTheme
import com.kyawlinnthant.theme.dimen

@Composable
fun ErrorItem(
    modifier: Modifier = Modifier,
    enabledFullScreen: Boolean = false,
    message: String,
    onRetry: () -> Unit,
) {
    Column(
        modifier =
            modifier
                .then(
                    if (enabledFullScreen) {
                        Modifier.fillMaxSize()
                    } else {
                        Modifier.fillMaxWidth()
                    },
                )
                .padding(MaterialTheme.dimen.base),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = message, style = MaterialTheme.typography.labelLarge)
        TextButton(onClick = onRetry) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}

@PreviewScreenSizes
@Composable
private fun Preview(
    @PreviewParameter(FullScreenProvider::class) enabled: Boolean,
) {
    LineManWongNaiTheme {
        ErrorItem(
            message = "Could not load data",
            enabledFullScreen = enabled,
        ) {
        }
    }
}
