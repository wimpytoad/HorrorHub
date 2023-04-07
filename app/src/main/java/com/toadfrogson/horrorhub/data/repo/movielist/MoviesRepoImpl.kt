package com.toadfrogson.horrorhub.data.repo.movielist

import com.toadfrogson.horrorhub.data.localData.dao.MoviesDao
import com.toadfrogson.horrorhub.data.response.ApiResponse
import com.toadfrogson.horrorhub.domain.api.GetMoviesApi
import com.toadfrogson.horrorhub.domain.model.movie.MovieListEntity
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.repo.MoviesRepo
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(private val api: GetMoviesApi, private val dao: MoviesDao): MoviesRepo {

    override suspend fun getSuggestedMovies(type: MovieListType): ApiResponse<MovieListEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieImagery(movieId: Int): ApiResponse<MoviePostersEntity> {
        TODO("Not yet implemented")
    }
}