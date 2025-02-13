package com.kyawlinnthant.presentation.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.kyawlinnthant.coins.presentation.R
import com.kyawlinnthant.theme.LineManWongNaiTheme
import com.kyawlinnthant.theme.dimen

@Composable
fun InviteFriendItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val text =
        buildAnnotatedString {
            append(stringResource(id = R.string.invite_friend_desc))
            append(" ")
            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                ),
            ) {
                append(stringResource(id = R.string.invite_friend))
            }
        }
    Card(
        modifier =
            modifier
                .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
        onClick = onClick,
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimen.base2x),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.base),
        ) {
            Image(
                painter = painterResource(id = R.drawable.invite),
                contentDescription = null,
                modifier = Modifier.size(MaterialTheme.dimen.base6x),
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimen.base))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    LineManWongNaiTheme {
        InviteFriendItem(onClick = {})
    }
}
