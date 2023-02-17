package com.toadfrogson.horrorhub.data.repo.movielist

import com.toadfrogson.horrorhub.data.client.WebClient
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieEntity
import com.toadfrogson.horrorhub.data.response.ApiResponse

interface GetMoviesApi {
    suspend fun getSuggestedMovies() : ApiResponse<MovieEntity>
}
class GetMoviesApiImpl(private val client: WebClient) : GetMoviesApi {
    val endpoint = "3/movie/550?api_key=8c241427387d1477f9214911ad446bbb"
    override suspend fun getSuggestedMovies(): ApiResponse<MovieEntity> {
        val response = client.makeClientGet<MovieEntity>(endpoint)
        return response
    }
}