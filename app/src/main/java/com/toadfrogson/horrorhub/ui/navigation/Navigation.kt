package com.toadfrogson.horrorhub.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.toadfrogson.horrorhub.presentation.viewmodel.MovieListViewModel
import com.toadfrogson.horrorhub.ui.screen.MovieDetailsScreen
import com.toadfrogson.horrorhub.ui.screen.MovieListScreen
import com.toadfrogson.horrorhub.ui.screen.movieDetailsRoute
import com.toadfrogson.horrorhub.ui.screen.movieScreenRoute
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(navController: NavHostController) {
    val movieViewModel: MovieListViewModel = koinViewModel()
    NavHost(navController = navController, startDestination = movieScreenRoute) {
        composable(movieScreenRoute) {
            MovieListScreen(navController = navController, viewModel = movieViewModel)
        }

        composable(movieDetailsRoute)
        {
            MovieDetailsScreen(viewModel = movieViewModel)
        }
    }
}