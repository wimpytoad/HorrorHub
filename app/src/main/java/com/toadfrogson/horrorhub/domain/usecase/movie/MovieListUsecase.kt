package com.toadfrogson.horrorhub.domain.usecase.movie

import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType.CULT_CLASSIC
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieEntity
import com.toadfrogson.horrorhub.domain.model.movie.transformed.MovieUIModel
import com.toadfrogson.horrorhub.domain.repo.MoviesRepo
import com.toadfrogson.horrorhub.domain.repo.repoResult.RepoResult.Failure
import com.toadfrogson.horrorhub.domain.repo.repoResult.RepoResult.Success
import com.toadfrogson.horrorhub.domain.usecase.UseCase
import com.toadfrogson.horrorhub.domain.usecase.UseCaseError
import com.toadfrogson.horrorhub.domain.usecase.UseCaseResult
import javax.inject.Inject

interface MovieListUseCase : UseCase<MovieListUseCase.Params, UseCaseResult<List<MovieEntity>>> {
    override suspend operator fun invoke(
        params: Params
    ): UseCaseResult<List<MovieEntity>>

    data class Params(
        val refreshContent: Boolean = false,
        val listType: MovieListType = CULT_CLASSIC
    )
}

class MovieListUseCaseImpl @Inject constructor(private val repo: MoviesRepo) : MovieListUseCase {
    override suspend operator fun invoke(
        params: MovieListUseCase.Params
    ): UseCaseResult<List<MovieEntity>> {
        return when (val repoResponse =
            repo.getSuggestedMovies(refresh = params.refreshContent, type = params.listType)) {
            is Success -> UseCaseResult.Success(repoResponse.data)
            is Failure -> UseCaseResult.Failure(UseCaseError())
        }
    }
}