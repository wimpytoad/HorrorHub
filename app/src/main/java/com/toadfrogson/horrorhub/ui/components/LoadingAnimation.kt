package com.toadfrogson.horrorhub.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec.RawRes
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoadingAnimation(modifier: Modifier = Modifier, animationModifier: Modifier = Modifier, lottieRes: Int) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val composition by rememberLottieComposition(RawRes(lottieRes))
        val progress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)
        LottieAnimation(
            modifier = animationModifier
                .size(128.dp)
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize(),
            composition = composition,
            progress = { progress },
        )
    }
}