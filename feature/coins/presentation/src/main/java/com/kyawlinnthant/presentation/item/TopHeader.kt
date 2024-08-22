package com.kyawlinnthant.presentation.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.kyawlinnthant.coins.presentation.R
import com.kyawlinnthant.domain.vo.CoinVo
import com.kyawlinnthant.theme.LineManWongNaiTheme
import com.kyawlinnthant.theme.dimen
import com.kyawlinnthant.util.CoinChange
import com.kyawlinnthant.util.CoinDisplayFormatter

@Composable
fun TopHeader(
    modifier: Modifier = Modifier,
    coin: CoinVo,
    onClick: () -> Unit,
) {
    val formattedChange = CoinDisplayFormatter.formatCoinChange(input = coin.change)

    val color =
        when (formattedChange) {
            CoinChange.Default -> MaterialTheme.colorScheme.onSurfaceVariant
            is CoinChange.Green -> Color.Green
            is CoinChange.Red -> Color.Red
        }

    val icon =
        when (formattedChange) {
            CoinChange.Default -> null
            is CoinChange.Green -> R.drawable.baseline_arrow_upward_24
            is CoinChange.Red -> R.drawable.baseline_arrow_downward_24
        }

    ElevatedCard(
        colors =
            CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
        onClick = onClick,
    ) {
        Column(
            modifier =
                modifier
                    .width(MaterialTheme.dimen.topRank)
                    .padding(vertical = MaterialTheme.dimen.base2x, horizontal = MaterialTheme.dimen.base),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                model =
                    ImageRequest.Builder(LocalContext.current)
                        .data(coin.iconUrl)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                contentDescription = null,
                modifier = modifier.size(MaterialTheme.dimen.base6x),
            )

            Spacer(modifier = modifier.height(MaterialTheme.dimen.base))

            Text(
                text = coin.symbol,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style =
                    MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ),
            )

            Spacer(modifier = modifier.height(MaterialTheme.dimen.base))

            Text(
                text = coin.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style =
                    MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = MaterialTheme.dimen.defaultAlpha),
                    ),
            )
            Spacer(modifier = modifier.height(MaterialTheme.dimen.base))

            Row(verticalAlignment = Alignment.CenterVertically) {
                icon?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = null,
                        tint = color,
                        modifier = modifier.size(MaterialTheme.dimen.base2x),
                    )
                }
                Text(
                    text = formattedChange.value,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style =
                        MaterialTheme.typography.labelMedium.copy(
                            color = color,
                        ),
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    LineManWongNaiTheme {
        TopHeader(
            coin =
                CoinVo(
                    name = "Internet Computer Definition ( ROYAL EXPRESS AND CHANGED SERVICES )",
                    symbol = "USD",
                    price = "11233.2345456",
                    change = "0.98",
                ),
        ) {
        }
    }
}

@Preview
@Composable
private fun NegativePreview() {
    LineManWongNaiTheme {
        TopHeader(
            coin =
                CoinVo(
                    name = "Internet Computer Definition ( ROYAL EXPRESS AND CHANGED SERVICES )",
                    symbol = "USD",
                    price = "11233.2345456",
                    change = "-0.98",
                ),
        ) {
        }
    }
}
