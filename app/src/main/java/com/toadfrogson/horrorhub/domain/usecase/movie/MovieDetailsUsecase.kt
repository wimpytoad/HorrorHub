package com.toadfrogson.horrorhub.domain.usecase.movie

import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.repo.MoviesRepo
import com.toadfrogson.horrorhub.domain.repo.repoResult.RepoResult.Failure
import com.toadfrogson.horrorhub.domain.repo.repoResult.RepoResult.Success
import com.toadfrogson.horrorhub.domain.usecase.UseCase
import com.toadfrogson.horrorhub.domain.usecase.movie.MovieDetailsUseCase.Params
import javax.inject.Inject

interface MovieDetailsUseCase : UseCase<Params, MoviePostersEntity> {
    override suspend operator fun invoke(params: Params) : MoviePostersEntity

    data class Params(val refreshContent: Boolean = false, val movieId: Int)
}

class MovieDetailsUseCaseImpl @Inject constructor(private val repo: MoviesRepo) :
    MovieDetailsUseCase {
    override suspend fun invoke(params: Params) : MoviePostersEntity {
        return when (val repoResponse = repo.getMovieImagery(refresh = params.refreshContent, movieId = params.movieId)) {
            is Success -> returnData(repoResponse.data)
            is Failure -> returnError()
        }
    }

    private fun returnData(postersEntity: MoviePostersEntity) : MoviePostersEntity {
        return postersEntity
    }

    private fun returnError() = MoviePostersEntity()
}