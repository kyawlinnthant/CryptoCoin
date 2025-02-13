package com.kyawlinnthant.presentation.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.kyawlinnthant.coins.presentation.R
import com.kyawlinnthant.domain.vo.CoinVo
import com.kyawlinnthant.domain.vo.Top3Rank
import com.kyawlinnthant.theme.LineManWongNaiTheme
import com.kyawlinnthant.theme.WindowType
import com.kyawlinnthant.theme.dimen

@Composable
fun Top3Header(
    windowWidth: WindowType,
    top3Rank: Top3Rank,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val horizontalAlignment =
        when (windowWidth) {
            WindowType.Compact -> Arrangement.SpaceBetween
            WindowType.Medium -> Arrangement.Center
            WindowType.Expanded -> Arrangement.Center
        }

    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface),
    ) {
        val top3 =
            buildAnnotatedString {
                append(stringResource(id = R.string.top))
                append(" ")
                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                    ),
                ) {
                    append(stringResource(id = R.string.three))
                }
                append(" ")
                append(stringResource(id = R.string.rank))
            }
        Text(
            text = top3,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        )
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimen.base2x),
            horizontalArrangement = horizontalAlignment,
        ) {
            TopHeader(coin = top3Rank.rank1, onClick = {
                onClick(top3Rank.rank1.uuid)
            })
            if (windowWidth != WindowType.Compact) {
                Spacer(modifier = Modifier.width(MaterialTheme.dimen.base2x))
            }
            TopHeader(coin = top3Rank.rank2, onClick = {
                onClick(top3Rank.rank2.uuid)
            })
            if (windowWidth != WindowType.Compact) {
                Spacer(modifier = Modifier.width(MaterialTheme.dimen.base2x))
            }
            TopHeader(coin = top3Rank.rank3, onClick = {
                onClick(top3Rank.rank3.uuid)
            })
        }
    }
}

@Composable
@Preview
private fun Preview() {
    LineManWongNaiTheme {
        Top3Header(
            top3Rank =
                Top3Rank(
                    rank1 =
                        CoinVo(
                            name = "Bitcoin",
                            symbol = "BTC",
                            change = "1.98",
                        ),
                    rank2 =
                        CoinVo(
                            name = "Either",
                            symbol = "ETH",
                            change = "0.98",
                        ),
                    rank3 =
                        CoinVo(
                            name = "Binance Coin",
                            symbol = "BNB",
                            change = "-0.98",
                        ),
                ),
            windowWidth = WindowType.Medium,
            onClick = {},
        )
    }
}
