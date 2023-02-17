package com.toadfrogson.horrorhub.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toadfrogson.horrorhub.data.repo.movielist.GetMoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(private val moviesApi: GetMoviesApi) : ViewModel() {

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            getContent()
        }
    }

    private suspend fun getContent() {
        withContext(Dispatchers.IO) {
            moviesApi.getSuggestedMovies()
        }.apply {
            this.data
        }
    }
}