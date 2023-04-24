package com.toadfrogson.horrorhub.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toadfrogson.horrorhub.domain.usecase.UseCaseResult.Success
import com.toadfrogson.horrorhub.domain.usecase.movie.GuessMovieDataSet
import com.toadfrogson.horrorhub.domain.usecase.movie.MovieGuessGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class GuessMovieViewModel @Inject constructor(private val guessMovieUseCase: MovieGuessGameUseCase) : ViewModel() {
    private val movies = MutableStateFlow(GuessMovieDataSet())
    val state: StateFlow<GuessMovieDataSet> = movies

    init {
        getMoviesToGuess()
    }

    private fun getMoviesToGuess() {
        viewModelScope.launch {
            val content = getContent()
            movies.value = content
        }
    }

    private suspend fun getContent(): GuessMovieDataSet {
        return withContext(Dispatchers.IO) {
            when (val result = guessMovieUseCase("tag_to_go_here")) {
                is Success -> result.data
                else -> GuessMovieDataSet() //TODO: handle error
            }
        }
    }

}

