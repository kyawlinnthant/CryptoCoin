package com.kyawlinnthant.presentation.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.kyawlinnthant.coins.presentation.R
import com.kyawlinnthant.domain.vo.CoinDetailVo
import com.kyawlinnthant.theme.dimen
import com.kyawlinnthant.util.CoinDisplayFormatter
import com.kyawlinnthant.util.CoinPriceDecimalPlace

@Composable
fun DetailSheet(
    modifier: Modifier = Modifier,
    coinDetail: CoinDetailVo,
    onGoWebsite: (String) -> Unit,
) {
    val formattedPrice =
        CoinDisplayFormatter.formatCoinPrice(
            input = coinDetail.price,
            desiredDecimalPlace = CoinPriceDecimalPlace.ForDetail,
        )
    val formattedCap = CoinDisplayFormatter.formatCoinMarketCap(coinDetail.marketCap)

    LazyColumn(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimen.base2x),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = rememberLazyListState(),
    ) {
        item {
            Row(modifier = modifier.fillMaxWidth()) {
                AsyncImage(
                    model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(coinDetail.image)
                            .decoderFactory(SvgDecoder.Factory())
                            .build(),
                    contentDescription = null,
                    modifier = modifier.size(MaterialTheme.dimen.base6x),
                )
                Spacer(modifier = modifier.width(MaterialTheme.dimen.base2x))
                Column(
                    modifier =
                        modifier
                            .fillMaxWidth()
                            .weight(1f),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = coinDetail.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style =
                                MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color =
                                        if (coinDetail.color.isNotEmpty()) {
                                            coinDetail.color.toColor()
                                                ?: MaterialTheme.colorScheme.onSurface
                                        } else {
                                            MaterialTheme.colorScheme.onSurface
                                        },
                                ),
                        )
                        Spacer(modifier = modifier.width(MaterialTheme.dimen.tiny))
                        Text(
                            text = "(${coinDetail.symbol})",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(id = R.string.price),
                            style =
                                MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                ),
                        )
                        Spacer(modifier = modifier.width(MaterialTheme.dimen.small))
                        Text(
                            text = formattedPrice,
                            style =
                                MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Normal,
                                ),
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(id = R.string.market),
                            style =
                                MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                ),
                        )
                        Spacer(modifier = modifier.width(MaterialTheme.dimen.small))
                        Text(
                            text = formattedCap,
                            style =
                                MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Normal,
                                ),
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = coinDetail.description.ifEmpty { stringResource(id = R.string.description) },
                style = MaterialTheme.typography.labelMedium,
                modifier = modifier.padding(vertical = MaterialTheme.dimen.base2x),
            )
        }
        item {
            if (coinDetail.website.isNotEmpty()) {
                HorizontalDivider()
                TextButton(onClick = {
                    onGoWebsite(coinDetail.website)
                }) {
                    Text(text = stringResource(id = R.string.website))
                }
            }
        }
        item { Spacer(modifier = modifier.navigationBarsPadding()) }
    }
}
