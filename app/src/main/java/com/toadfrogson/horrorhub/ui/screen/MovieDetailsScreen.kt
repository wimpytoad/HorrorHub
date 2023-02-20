package com.toadfrogson.horrorhub.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import coil.compose.AsyncImage
import com.toadfrogson.horrorhub.presentation.viewmodel.MovieListViewModel

const val movieDetailsRoute = "movie_details_screen"
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(viewModel: MovieListViewModel) {
    val selectedMovie = viewModel.selectedMovie
    val imagery = viewModel.imageryState.collectAsState().value
    Scaffold() {
        Column() {
            AsyncImage(model = getFullPosterUrl(imagery.backdrops?.random()?.file_path.orEmpty()), contentDescription = "")
            Text(text = "hello!")
        }
    }
}

//TODO make movie data class to transform entity
fun getFullPosterUrl(posterPath: String): String {
    return "https://image.tmdb.org/t/p/original/" + posterPath
}