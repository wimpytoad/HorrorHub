package com.toadfrogson.horrorhub.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toadfrogson.horrorhub.domain.model.movie.transformed.MovieData
import com.toadfrogson.horrorhub.domain.model.movie.transformed.MovieImageryData
import com.toadfrogson.horrorhub.domain.usecase.UseCaseResult.Success
import com.toadfrogson.horrorhub.domain.usecase.movie.GetMovieDetailsUseCase
import com.toadfrogson.horrorhub.domain.usecase.movie.MovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListUseCase: MovieListUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val movies = MutableStateFlow(emptyList<MovieData>())
    val state: StateFlow<List<MovieData>> = movies

    var selectedMovie: MovieData? by mutableStateOf(null)
        private set

    private val movieImagery = MutableStateFlow(MovieImageryData())
    val imageryState: StateFlow<MovieImageryData> = movieImagery

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val content = getContent()
            movies.value = content
        }
    }

    private suspend fun getContent(): List<MovieData> {
        return withContext(Dispatchers.IO) {
            when (val result = movieListUseCase(MovieListUseCase.Params())) {
                is Success -> result.data
                else -> emptyList() //TODO: handle error
            }
        }
    }

    fun selectItem(itemSelected: MovieData) {
        selectedMovie = itemSelected
        movieImagery.value = MovieImageryData()
        getSelectedMovieImagery()
    }

    private fun getSelectedMovieImagery() {
        val movieId = selectedMovie?.id ?: 0
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getMovieDetailsUseCase(GetMovieDetailsUseCase.Params(movieId = movieId))
            }.apply {
                when (this) {
                    is Success -> movieImagery.value = this.data
                    else -> return@apply //TODO: Handle error
                }
            }
        }
    }
}