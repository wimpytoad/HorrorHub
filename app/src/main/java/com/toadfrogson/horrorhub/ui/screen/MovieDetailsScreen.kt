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
import com.toadfrogson.horrorhub.presentation.viewmodel.MovieListViewModel
import com.toadfrogson.horrorhub.ui.components.ImageSlideShow

const val movieDetailsRoute = "movie_details_screen"

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(viewModel: MovieListViewModel) {
    val selectedMovie = viewModel.selectedMovie
    val imagery = viewModel.imageryState.collectAsState().value
    Scaffold() {
        Column() {
            MovieDetailsHeader(
                selectedMovie?.original_title.orEmpty(),
                selectedMovie?.release_date.orEmpty(),
                selectedMovie?.runtime.toString()
            )

            val imageUrls =
                imagery.backdrops?.mapNotNull { it.file_path.takeIf { path -> path?.isNotEmpty() == true } }

            if (!imageUrls.isNullOrEmpty()) {
                ImageSlideShow(imageUrls = imageUrls)
            }
            Text(text = "hello!")
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

//TODO make movie data class to transform entity
fun getFullPosterUrl(posterPath: String): String {
    return "https://image.tmdb.org/t/p/original/" + posterPath
}