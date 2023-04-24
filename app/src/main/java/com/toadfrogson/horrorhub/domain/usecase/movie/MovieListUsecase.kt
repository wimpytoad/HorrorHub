package com.toadfrogson.horrorhub.domain.usecase.movie

import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResult.Failure
import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResult.Success
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType.CULT_CLASSIC
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieEntity
import com.toadfrogson.horrorhub.domain.model.movie.transformed.MovieData
import com.toadfrogson.horrorhub.domain.repo.MoviesRepo
import com.toadfrogson.horrorhub.domain.usecase.UseCase
import com.toadfrogson.horrorhub.domain.usecase.UseCaseError
import com.toadfrogson.horrorhub.domain.usecase.UseCaseResult
import javax.inject.Inject

interface MovieListUseCase : UseCase<MovieListUseCase.Params, UseCaseResult<List<MovieData>>> {
    override suspend operator fun invoke(
        params: Params
    ): UseCaseResult<List<MovieData>>

    data class Params(
        val refreshContent: Boolean = false,
        val listType: MovieListType = CULT_CLASSIC
    )
}

class MovieListUseCaseImpl @Inject constructor(private val repo: MoviesRepo) : MovieListUseCase {
    override suspend operator fun invoke(
        params: MovieListUseCase.Params
    ): UseCaseResult<List<MovieData>> {
        return when (val repoResponse =
            repo.getSuggestedMovies(refresh = params.refreshContent, type = params.listType)) {
            is Success -> UseCaseResult.Success(transformData(repoResponse.data))
            is Failure -> UseCaseResult.Failure(UseCaseError())
        }
    }

    private fun transformData(entities: List<MovieEntity>) : List<MovieData> {
        return entities.map { MovieData.convertFromEntity(it) }
    }
}