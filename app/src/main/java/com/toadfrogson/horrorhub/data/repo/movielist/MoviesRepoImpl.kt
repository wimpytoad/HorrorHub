package com.toadfrogson.horrorhub.data.repo.movielist

import com.toadfrogson.horrorhub.data.localData.dao.MoviesDao
import com.toadfrogson.horrorhub.data.localData.model.MovieDbEntity
import com.toadfrogson.horrorhub.domain.api.GetMoviesApi
import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResult
import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResult.Failure
import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResult.Success
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieEntity
import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.repo.MoviesRepo
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(private val api: GetMoviesApi, private val dao: MoviesDao): MoviesRepo {

    override suspend fun getSuggestedMovies(refresh: Boolean, type: MovieListType): ApiResult<List<MovieEntity>> {
        if (!refresh) {
            return Success(dao.getAll().map { MovieDbEntity.toMovieEntity(it) } )
        }
        val apiResponse = api.getSuggestedMoviesRemote(type)

        if (apiResponse is Failure)  {
            return apiResponse
        } else {
            apiResponse as Success
        }

        //save response
        dao.insertAll(apiResponse.data.items.map { MovieDbEntity(it) })

        //return result
        return Success(apiResponse.data.items)
    }

    override suspend fun getMovieImagery(refresh: Boolean, movieId: Int): ApiResult<MoviePostersEntity> {
        val apiResponse = api.getMovieImageryRemote(movieId)
        if (apiResponse is Failure) {
            return Failure("no content!")
        } else {
            apiResponse as Success
        }
        return Success(apiResponse.data)
    }
}