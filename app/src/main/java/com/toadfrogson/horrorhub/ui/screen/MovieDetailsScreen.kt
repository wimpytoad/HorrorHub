package com.toadfrogson.horrorhub.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader.Builder
import coil.compose.rememberAsyncImagePainter
import com.toadfrogson.horrorhub.presentation.viewmodel.MovieListViewModel
import kotlinx.coroutines.delay

const val movieDetailsRoute = "movie_details_screen"

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(viewModel: MovieListViewModel) {
    val selectedMovie = viewModel.selectedMovie
    val imagery = viewModel.imageryState.collectAsState().value
    Scaffold() {
        Column() {
            val imageUrls = imagery.backdrops?.mapNotNull { it.file_path.takeIf { path -> path?.isNotEmpty() == true } }

            if (!imageUrls.isNullOrEmpty()) {
                PosterSlideShow(imageUrls = imageUrls)
            }
            Text(text = "hello!")
        }
    }
}

//TODO make movie data class to transform entity
fun getFullPosterUrl(posterPath: String): String {
    return "https://image.tmdb.org/t/p/original/" + posterPath
}

@Composable
fun PosterSlideShow(imageUrls: List<String>) {
    var index by remember { mutableStateOf(0) }
    var selectedImageUrl by remember { mutableStateOf(imageUrls[index]) }
    val context = LocalContext.current
    val imageLoader = remember {
        Builder(context)
            .crossfade(true)
            .build()
    }
    val painter =
        rememberAsyncImagePainter(getFullPosterUrl(selectedImageUrl), imageLoader = imageLoader)
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            index = (index + 1) % imageUrls.size
            selectedImageUrl = imageUrls[index]
        }
    }
    Image(painter = painter, contentDescription = "", modifier = Modifier.fillMaxSize())

}