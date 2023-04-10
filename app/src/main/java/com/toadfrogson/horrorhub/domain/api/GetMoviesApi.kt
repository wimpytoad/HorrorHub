package com.toadfrogson.horrorhub.domain.api

import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResponse
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieListEntity
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType.CULT_CLASSIC
import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity

interface GetMoviesApi {
    suspend fun getSuggestedMoviesRemote(type: MovieListType = CULT_CLASSIC) : ApiResponse<MovieListEntity>
    suspend fun getMovieImageryRemote(movieId: Int) : ApiResponse<MoviePostersEntity>
}