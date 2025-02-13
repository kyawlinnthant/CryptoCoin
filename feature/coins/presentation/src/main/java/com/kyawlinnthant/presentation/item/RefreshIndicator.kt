package com.kyawlinnthant.presentation.item

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefreshIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.kyawlinnthant.theme.dimen
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefreshIndicator(
    state: PullToRefreshState,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
    crossFadeDuration: Duration = 3.seconds,
) {
    Box(
        modifier =
            modifier.pullToRefreshIndicator(
                state = state,
                isRefreshing = isRefreshing,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Crossfade(
            targetState = isRefreshing,
            animationSpec = tween(durationMillis = crossFadeDuration.toInt(DurationUnit.SECONDS)),
            modifier = Modifier.align(Alignment.Center),
        ) { refreshing ->
            if (refreshing) {
                CircularProgressIndicator()
            } else {
                val distanceFraction = { state.distanceFraction.coerceIn(0f, 1f) }
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Refresh",
                    modifier =
                        Modifier
                            .size(MaterialTheme.dimen.base2x)
                            .graphicsLayer {
                                val progress = distanceFraction()
                                this.alpha = progress
                                this.scaleX = progress
                                this.scaleY = progress
                            },
                )
            }
        }
    }
}
