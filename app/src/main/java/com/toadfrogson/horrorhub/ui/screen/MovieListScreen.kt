package com.toadfrogson.horrorhub.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.toadfrogson.horrorhub.presentation.viewmodel.MovieListViewModel
import com.toadfrogson.horrorhub.ui.components.MovieListItem
import org.koin.androidx.compose.koinViewModel

const val movieScreenRoute = "movie_screen_view"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(navController: NavHostController, viewModel: MovieListViewModel ) {

    Scaffold(

    ) {
        val movies = viewModel.state.collectAsState().value
        movies.items?.let {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(it) { movie ->
                    MovieListItem(data = movie, onSelected = {
                        viewModel.selectItem(movie)
                        navController.navigate(movieDetailsRoute)
                    })
                }
            }
        }


    }
}