package com.toadfrogson.horrorhub.domain.repo

import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResult
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType.CULT_CLASSIC
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieEntity
import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity

interface MoviesRepo {
    suspend fun getSuggestedMovies(refresh: Boolean = true, type: MovieListType = CULT_CLASSIC): ApiResult<List<MovieEntity>>
    suspend fun getMovieImagery(refresh: Boolean = true, movieId: Int): ApiResult<MoviePostersEntity>
}