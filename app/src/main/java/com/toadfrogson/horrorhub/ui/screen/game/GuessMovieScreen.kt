package com.toadfrogson.horrorhub.ui.screen.game

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.toadfrogson.horrorhub.presentation.viewmodel.GuessMovieViewModel

const val guessMovieGameRoute = "guess_movie_game_screen"


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GuessMovieScreen(navController: NavController, viewModel: GuessMovieViewModel = hiltViewModel()) {
    val data = viewModel.state.collectAsState().value
    Scaffold {
        Column {
        LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
            items(data.movieScreens) {
                AsyncImage(
                    model = it,
                    contentDescription = "movie to guess",
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
                        .weight(0.4f)
                )
            }
        })
            LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
                items(data.answers) {
                    Text(text = it)
                }
            })
        }
    }
}