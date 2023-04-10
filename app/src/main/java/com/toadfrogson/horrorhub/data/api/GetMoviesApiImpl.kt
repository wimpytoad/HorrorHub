package com.toadfrogson.horrorhub.data.api

import com.toadfrogson.horrorhub.data.client.WebClient
import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResponse
import com.toadfrogson.horrorhub.domain.model.MovieDBEndpoints
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieListEntity
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.api.GetMoviesApi
import javax.inject.Inject


class GetMoviesApiImpl @Inject constructor(private val client: WebClient) : GetMoviesApi {
    override suspend fun getSuggestedMoviesRemote(type: MovieListType): ApiResponse<MovieListEntity> {
        val endpoint = MovieDBEndpoints.MovieListEndpoint(type = type).toString()
        return client.makeClientGet(endpoint = endpoint)
    }

    override suspend fun getMovieImageryRemote(movieId: Int): ApiResponse<MoviePostersEntity> {
        val endpoint = MovieDBEndpoints.MovieDetailsEndpoint(movieId = movieId).toString()
        return client.makeClientGet(endpoint = endpoint)
    }
}