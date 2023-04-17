package com.toadfrogson.horrorhub.domain.api

import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResult
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieListEntity
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType.CULT_CLASSIC
import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity

interface GetMoviesApi {
    suspend fun getSuggestedMoviesRemote(type: MovieListType = CULT_CLASSIC): ApiResult<MovieListEntity>
    suspend fun getMovieImageryRemote(movieId: Int): ApiResult<MoviePostersEntity>
}