package com.toadfrogson.horrorhub.ui.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.toadfrogson.horrorhub.ui.utils.transitionDuration

@ExperimentalAnimationApi
fun NavGraphBuilder.presentComposable(
    route: String,
    arguments: List<NamedNavArgument> = listOf(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(route,
        arguments = arguments,
        enterTransition = { ->
            slideInVertically(initialOffsetY = { it }, animationSpec = tween(transitionDuration))
        }, popExitTransition = { ->
            slideOutVertically(targetOffsetY = { it }, animationSpec = tween(transitionDuration))
        }, popEnterTransition = { ->
            fadeIn(initialAlpha = 0.5f, animationSpec = tween(transitionDuration))
        }, content = content)
}