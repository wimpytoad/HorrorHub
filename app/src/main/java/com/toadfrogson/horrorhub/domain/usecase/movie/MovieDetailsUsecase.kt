package com.toadfrogson.horrorhub.domain.usecase.movie

import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResult.Failure
import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResult.Success
import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.repo.MoviesRepo
import com.toadfrogson.horrorhub.domain.usecase.UseCase
import com.toadfrogson.horrorhub.domain.usecase.UseCaseError
import com.toadfrogson.horrorhub.domain.usecase.UseCaseResult
import com.toadfrogson.horrorhub.domain.usecase.movie.GetMovieDetailsUseCase.Params
import javax.inject.Inject

interface GetMovieDetailsUseCase : UseCase<Params, UseCaseResult<MoviePostersEntity>> {
    override suspend operator fun invoke(params: Params) : UseCaseResult<MoviePostersEntity>

    data class Params(val refreshContent: Boolean = false, val movieId: Int)
}

class GetMovieDetailsUseCaseImpl @Inject constructor(private val repo: MoviesRepo) :
    GetMovieDetailsUseCase {
    override suspend fun invoke(params: Params) : UseCaseResult<MoviePostersEntity> {
        return when (val repoResponse = repo.getMovieImagery(refresh = params.refreshContent, movieId = params.movieId)) {
            is Success -> UseCaseResult.Success(repoResponse.data)
            is Failure -> UseCaseResult.Failure(UseCaseError())
        }
    }
}