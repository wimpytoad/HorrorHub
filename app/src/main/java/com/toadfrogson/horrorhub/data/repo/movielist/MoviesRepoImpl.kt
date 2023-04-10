package com.toadfrogson.horrorhub.data.repo.movielist

import com.toadfrogson.horrorhub.data.localData.dao.MoviesDao
import com.toadfrogson.horrorhub.data.localData.model.MovieDBEntity
import com.toadfrogson.horrorhub.domain.api.GetMoviesApi
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.model.movie.transformed.MovieUIModel
import com.toadfrogson.horrorhub.domain.repo.MoviesRepo
import com.toadfrogson.horrorhub.domain.repo.repoResult.RepoResult
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(private val api: GetMoviesApi, private val dao: MoviesDao): MoviesRepo {

    override suspend fun getSuggestedMovies(refresh: Boolean, type: MovieListType): RepoResult<List<MovieUIModel>> {
        if (!refresh) {
            return RepoResult.Success(dao.getAll().map { MovieDBEntity.convertToUiModel(it) })
        }
        val apiResponse = api.getSuggestedMoviesRemote(type)
        if (!apiResponse.success || apiResponse.data == null)  {
            return RepoResult.Failure("no content!")
        }

        val transformedData = apiResponse.data.items.map {
            MovieUIModel.convertFromEntity(it)
        }
        //save response
        dao.insertAll(transformedData.map { MovieDBEntity.convertFromUiModel(it) })

        //return result
        return RepoResult.Success(transformedData)
    }

    override suspend fun getMovieImagery(refresh: Boolean, movieId: Int): RepoResult<MoviePostersEntity> {
        val apiResponse = api.getMovieImageryRemote(movieId)
        if (!apiResponse.success || apiResponse.data == null) {
            return RepoResult.Failure("no content!")
        }
        return RepoResult.Success(apiResponse.data)
    }
}