package com.toadfrogson.horrorhub.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.model.movie.transformed.MovieUIModel
import com.toadfrogson.horrorhub.domain.repo.MoviesRepo
import com.toadfrogson.horrorhub.domain.repo.repoResult.RepoResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repo: MoviesRepo) : ViewModel() {

    private val movies = MutableStateFlow(emptyList<MovieUIModel>())
    val state: StateFlow<List<MovieUIModel>> = movies
    var isLoading by mutableStateOf(true)

    var selectedMovie: MovieUIModel? by mutableStateOf(null)
        private set

    private val movieImagery = MutableStateFlow(MoviePostersEntity())
    val imageryState: StateFlow<MoviePostersEntity> = movieImagery

    init {
        getMovies()
    }

    private fun getMovies() {
        isLoading = true
        viewModelScope.launch {
            val content = getContent()
            movies.value = content
            isLoading = false
        }
    }

    private suspend fun getContent(): List<MovieUIModel> {
        withContext(Dispatchers.IO) {
            repo.getSuggestedMovies()
        }.apply {
            return when (this) {
                is RepoResult.Success -> this.data
                is RepoResult.Failure -> emptyList()
            }
        }
    }

    fun selectItem(itemSelected: MovieUIModel) {
        selectedMovie = itemSelected
        getSelectedMovieImagery()
    }

    private fun getSelectedMovieImagery() {
        val movieId = selectedMovie?.id ?: 0
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.getMovieImagery(false, movieId)
            }.apply {
                if (this is RepoResult.Success) {
                    movieImagery.value = this.data
                }
            }
        }
    }
}