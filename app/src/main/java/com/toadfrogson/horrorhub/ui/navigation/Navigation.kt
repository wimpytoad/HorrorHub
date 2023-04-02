package com.toadfrogson.horrorhub.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.toadfrogson.horrorhub.ui.screen.MovieDetailsScreen
import com.toadfrogson.horrorhub.ui.screen.MovieListScreen
import com.toadfrogson.horrorhub.ui.screen.movieDetailsRoute
import com.toadfrogson.horrorhub.ui.screen.movieScreenRoute

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = movieScreenRoute) {
        composable(movieScreenRoute) {
            MovieListScreen(navController = navController)
        }

        composable(movieDetailsRoute)
        {
            MovieDetailsScreen()
        }
    }
}