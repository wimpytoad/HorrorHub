package com.toadfrogson.horrorhub.data.api

import com.toadfrogson.horrorhub.data.client.WebClient
import com.toadfrogson.horrorhub.domain.model.MovieDBEndpoints
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieListEntity
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.api.GetMoviesApi
import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResult
import javax.inject.Inject


class GetMoviesApiImpl @Inject constructor(private val client: WebClient) : GetMoviesApi {
    override suspend fun getSuggestedMoviesRemote(type: MovieListType): ApiResult<MovieListEntity> {
        val endpoint = MovieDBEndpoints.MovieListEndpoint(type = type).toString()
        return client.makeClientGet(endpoint = endpoint)
    }

    override suspend fun getMovieImageryRemote(movieId: Int): ApiResult<MoviePostersEntity> {
        val endpoint = MovieDBEndpoints.MovieDetailsEndpoint(movieId = movieId).toString()
        return client.makeClientGet(endpoint = endpoint)
    }
}