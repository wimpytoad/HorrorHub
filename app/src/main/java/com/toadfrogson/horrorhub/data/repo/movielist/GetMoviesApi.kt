package com.toadfrogson.horrorhub.data.repo.movielist

import com.toadfrogson.horrorhub.data.client.WebClient
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieEntity
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieListEntity
import com.toadfrogson.horrorhub.data.response.ApiResponse

interface GetMoviesApi {
    suspend fun getSuggestedMovies() : ApiResponse<MovieListEntity>
}
class GetMoviesApiImpl(private val client: WebClient) : GetMoviesApi {
    val endpoint = "/3/list/8240716"
    override suspend fun getSuggestedMovies(): ApiResponse<MovieListEntity> {
        val response = client.makeClientGet<MovieListEntity>(endpoint)
        return response
    }
}