package com.toadfrogson.horrorhub.domain.repo

import com.toadfrogson.horrorhub.data.response.ApiResponse
import com.toadfrogson.horrorhub.domain.model.movie.MovieListEntity
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType.CULT_CLASSIC
import com.toadfrogson.horrorhub.domain.model.movie.MoviePostersEntity

interface MoviesRepo {
    suspend fun getSuggestedMovies(type: MovieListType = CULT_CLASSIC) : ApiResponse<MovieListEntity>
    suspend fun getMovieImagery(movieId: Int) : ApiResponse<MoviePostersEntity>
}