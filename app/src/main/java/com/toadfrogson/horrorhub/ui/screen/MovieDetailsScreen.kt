package com.toadfrogson.horrorhub.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.toadfrogson.horrorhub.presentation.viewmodel.MovieListViewModel
import com.toadfrogson.horrorhub.ui.components.ImageSlideShow

const val movieDetailsRoute = "movie_details_screen"

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(navController: NavController, viewModel: MovieListViewModel) {
    val selectedMovie = viewModel.selectedMovie
    val imagery = viewModel.imageryState.collectAsState().value
    selectedMovie?.let {movie ->
        Scaffold {
            Column {
                MovieDetailsHeader(
                    movie.originalTitle,
                    movie.releaseDate,
                    movie.runtime.toString()
                )

                val imageUrls =
                    imagery.backdrops.mapNotNull { it.filePath.takeIf { path -> path.isNotEmpty() } }

                if (imageUrls.isNotEmpty()) {
                    ImageSlideShow(imageUrls = imageUrls)
                }
                Text(text = "")
            }
        }
    }
}

@Composable
fun MovieDetailsHeader(title: String, year: String, duration: String) {
    Column() {
        Text(text = title, style = MaterialTheme.typography.headlineLarge)
        Row() {
            Text(text = year, style = MaterialTheme.typography.bodyMedium)
            Text(text = duration, style = MaterialTheme.typography.bodyMedium)
        }
    }
}