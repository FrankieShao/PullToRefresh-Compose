package io.github.frankieshao.refreshlayout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

/**
 * @author Frank Shao
 * @created 28/05/2024
 * Description: Animation loader for Lottie animation.
 */
@Composable
fun AnimationLoader(
    modifier: Modifier = Modifier,
    isPlaying: Boolean = false,
    @androidx.annotation.RawRes resId: Int
) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(resId)
    )
    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying
    )
    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
    )
}

data class AnimatorSpec(
    @androidx.annotation.RawRes
    val resId: Int,
    val width: Int,
    val height: Int
)
