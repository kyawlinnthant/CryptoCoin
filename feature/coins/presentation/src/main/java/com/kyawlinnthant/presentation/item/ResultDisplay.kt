package com.kyawlinnthant.presentation.item

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import com.kyawlinnthant.domain.vo.RequestState

@Composable
fun <T> ResultDisplayView(
    onLoading: @Composable () -> Unit,
    onSuccess: @Composable (T) -> Unit,
    onError: @Composable (String) -> Unit,
    requestState: RequestState<T>,
    onIdle: (@Composable () -> Unit)? = null,
) {
    AnimatedContent(
        targetState = requestState,
        transitionSpec = {
            fadeIn(tween(durationMillis = 300)) togetherWith
                fadeOut(tween(durationMillis = 300))
        },
        label = "Content Animation",
    ) { state ->
        when (state) {
            is RequestState.Idle -> {
                onIdle?.invoke()
            }

            is RequestState.Loading -> {
                onLoading()
            }

            is RequestState.Success -> {
                onSuccess(state.getSuccessData())
            }

            is RequestState.Error -> {
                onError(state.getErrorMessage())
            }
        }
    }
}
