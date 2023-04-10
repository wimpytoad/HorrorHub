package com.toadfrogson.horrorhub.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.toadfrogson.horrorhub.R
import com.toadfrogson.horrorhub.presentation.viewmodel.MovieListViewModel
import com.toadfrogson.horrorhub.ui.components.LoadingAnimation
import com.toadfrogson.horrorhub.ui.components.MovieListItem


const val movieScreenRoute = "movie_screen_view"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(navController: NavHostController, viewModel: MovieListViewModel) {

    Scaffold {
        val showLoadingAnimation = viewModel.isLoading
        if (showLoadingAnimation) {
            LoadingAnimation(lottieRes = R.raw.skull_bone_loading)
        }

        val movies = viewModel.state.collectAsState().value
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(movies) { movie ->
                MovieListItem(data = movie, onSelected = {
                    viewModel.selectItem(movie)
                    navController.navigate(movieDetailsRoute)
                })
            }
        }
    }
}
