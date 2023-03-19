package com.toadfrogson.horrorhub.data.repo.movielist

import com.toadfrogson.horrorhub.data.client.WebClient
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieDBEndpoints
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieEntity
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieListEntity
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieListType
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieListType.CULT_CLASSIC
import com.toadfrogson.horrorhub.data.repo.movielist.model.MoviePostersEntity
import com.toadfrogson.horrorhub.data.response.ApiResponse

interface GetMoviesApi {
    suspend fun getSuggestedMovies(type: MovieListType = CULT_CLASSIC) : ApiResponse<MovieListEntity>
    suspend fun getMovieImagery(movieId: Int) : ApiResponse<MoviePostersEntity>
}
class GetMoviesApiImpl(private val client: WebClient) : GetMoviesApi {
    override suspend fun getSuggestedMovies(type: MovieListType): ApiResponse<MovieListEntity> {
        val endpoint = MovieDBEndpoints.MovieListEndpoint(type = type).toString()
        val response = client.makeClientGet<MovieListEntity>(endpoint = endpoint)
        return response
    }

    override suspend fun getMovieImagery(movieId: Int): ApiResponse<MoviePostersEntity> {
        val endpoint = MovieDBEndpoints.MovieDetailsEndpoint(movieId = movieId).toString()
        val response = client.makeClientGet<MoviePostersEntity>(endpoint = endpoint)
        return response
    }
}