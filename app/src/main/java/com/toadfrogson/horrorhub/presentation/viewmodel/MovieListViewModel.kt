package com.toadfrogson.horrorhub.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieEntity
import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.model.movie.transformed.MovieUIModel
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

    private val movies = MutableStateFlow(emptyList<MovieUIModel>())
    val state: StateFlow<List<MovieUIModel>> = movies

    var selectedMovie: MovieUIModel? by mutableStateOf(null)
        private set

    private val movieImagery = MutableStateFlow(MoviePostersEntity())
    val imageryState: StateFlow<MoviePostersEntity> = movieImagery

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val content = getContent()
            movies.value = content
        }
    }

    private suspend fun getContent(): List<MovieUIModel> {
        return withContext(Dispatchers.IO) {
            when (val result = movieListUseCase(MovieListUseCase.Params())) {
                is Success -> transformData(result.data)
                else -> emptyList() //TODO: handle error
            }
        }
    }

    //TODO: move to separate mapping util class
    private fun transformData(entities: List<MovieEntity>) : List<MovieUIModel> {
        return entities.map { MovieUIModel.convertFromEntity(it) }
    }

    fun selectItem(itemSelected: MovieUIModel) {
        selectedMovie = itemSelected
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