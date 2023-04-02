package com.toadfrogson.horrorhub.data.repo.movielist

import com.toadfrogson.horrorhub.data.client.WebClient
import com.toadfrogson.horrorhub.data.response.ApiResponse
import com.toadfrogson.horrorhub.domain.model.MovieDBEndpoints
import com.toadfrogson.horrorhub.domain.model.movie.MovieListEntity
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.repo.GetMoviesApi
import javax.inject.Inject


class GetMoviesApiImpl @Inject constructor(private val client: WebClient) : GetMoviesApi {
    override suspend fun getSuggestedMovies(type: MovieListType): ApiResponse<MovieListEntity> {
        val endpoint = MovieDBEndpoints.MovieListEndpoint(type = type).toString()
        return client.makeClientGet(endpoint = endpoint)
    }

    override suspend fun getMovieImagery(movieId: Int): ApiResponse<MoviePostersEntity> {
        val endpoint = MovieDBEndpoints.MovieDetailsEndpoint(movieId = movieId).toString()
        return client.makeClientGet(endpoint = endpoint)
    }
}