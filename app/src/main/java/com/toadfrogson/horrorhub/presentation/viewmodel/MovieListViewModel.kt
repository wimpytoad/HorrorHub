package com.toadfrogson.horrorhub.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toadfrogson.horrorhub.data.repo.movielist.GetMoviesApi
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieEntity
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieListEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(private val moviesApi: GetMoviesApi) : ViewModel() {

    private val movies =  MutableStateFlow<MovieListEntity>(MovieListEntity("", emptyList()))
    val state: StateFlow<MovieListEntity> = movies

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
}