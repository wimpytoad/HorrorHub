package com.toadfrogson.horrorhub.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toadfrogson.horrorhub.data.repo.movielist.GetMoviesApi
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieEntity
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieListEntity
import com.toadfrogson.horrorhub.data.repo.movielist.model.MoviePostersEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(private val moviesApi: GetMoviesApi) : ViewModel() {

    private val movies = MutableStateFlow<MovieListEntity>(MovieListEntity("", emptyList()))
    val state: StateFlow<MovieListEntity> = movies

    var selectedMovie: MovieEntity? by mutableStateOf(null)
        private set

    private val movieImagery = MutableStateFlow(MoviePostersEntity())
    val imageryState: StateFlow<MoviePostersEntity> = movieImagery

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val content = getContent()
            content?.let {
                movies.value = it
            }
        }
    }

    private suspend fun getContent(): MovieListEntity? {
        withContext(Dispatchers.IO) {
            moviesApi.getSuggestedMovies()
        }.apply {
            return this.data
        }
    }

    fun selectItem(itemSelected: MovieEntity) {
        selectedMovie = itemSelected
        getSelectedMovieImagery(itemSelected.id)
    }

    private fun getSelectedMovieImagery(selectedMovieId: Int?) {
        val movieId = selectedMovie?.id ?: 0
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                moviesApi.getMovieImagery(movieId)
            }.apply {
                this.data?.let {
                    movieImagery.value = it
                }
            }
        }
    }
}