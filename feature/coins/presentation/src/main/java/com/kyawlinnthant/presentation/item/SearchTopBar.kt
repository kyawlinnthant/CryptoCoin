package com.kyawlinnthant.presentation.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.kyawlinnthant.coins.presentation.R
import com.kyawlinnthant.presentation.preview.SearchKeywordProvider
import com.kyawlinnthant.theme.LineManWongNaiTheme
import com.kyawlinnthant.theme.dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    searchPlaceholder: String = stringResource(id = R.string.search),
    onTextChanged: (String) -> Unit = {},
    onClearQueryClicked: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Row(
                modifier =
                    modifier
                        .fillMaxWidth()
                        .height(height = MaterialTheme.dimen.base6x)
                        .padding(end = MaterialTheme.dimen.base2x)
                        .clip(shape = MaterialTheme.shapes.medium)
                        .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                        .padding(start = MaterialTheme.dimen.base2x),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = null,
                )
                Spacer(modifier = modifier.width(MaterialTheme.dimen.base))
                BasicTextField(
                    modifier =
                        modifier
                            .fillMaxWidth()
                            .weight(
                                weight = 1f,
                                fill = false,
                            ),
                    onValueChange = onTextChanged,
                    textStyle =
                        MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                    value = searchQuery,
                    decorationBox = {
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = searchPlaceholder,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                        it()
                    },
                    singleLine = true,
                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search,
                        ),
                    cursorBrush =
                        SolidColor(
                            value = MaterialTheme.colorScheme.primary,
                        ),
                )
                Spacer(modifier = modifier.width(MaterialTheme.dimen.base))
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = onClearQueryClicked) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_close_24),
                            contentDescription = null,
                        )
                    }
                }
            }
        },
    )
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(SearchKeywordProvider::class) keyword: String,
) {
    LineManWongNaiTheme {
        SearchTopBar(searchQuery = keyword)
    }
}
