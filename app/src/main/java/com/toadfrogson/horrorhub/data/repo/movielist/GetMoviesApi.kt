package com.toadfrogson.horrorhub.data.repo.movielist

import com.toadfrogson.horrorhub.data.client.WebClient
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieEntity
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieListEntity
import com.toadfrogson.horrorhub.data.repo.movielist.model.MoviePostersEntity
import com.toadfrogson.horrorhub.data.response.ApiResponse

interface GetMoviesApi {
    suspend fun getSuggestedMovies() : ApiResponse<MovieListEntity>
    suspend fun getMovieImagery(movieId: Int) : ApiResponse<MoviePostersEntity>
}
class GetMoviesApiImpl(private val client: WebClient) : GetMoviesApi {
    override suspend fun getSuggestedMovies(): ApiResponse<MovieListEntity> {
        val endpoint = "/3/list/8240716"
        val response = client.makeClientGet<MovieListEntity>(endpoint)
        return response
    }

    override suspend fun getMovieImagery(movieId: Int): ApiResponse<MoviePostersEntity> {
        val endpoint = "3/movie/$movieId/images"
        val response = client.makeClientGet<MoviePostersEntity>(endpoint)
        return response
    }
}