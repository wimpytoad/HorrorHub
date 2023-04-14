package com.toadfrogson.horrorhub.domain.usecase

import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.repo.MoviesRepo
import com.toadfrogson.horrorhub.domain.repo.repoResult.RepoResult.Failure
import com.toadfrogson.horrorhub.domain.repo.repoResult.RepoResult.Success
import javax.inject.Inject

interface MovieDetailsUseCase {
    suspend operator fun invoke(refresh: Boolean, movieId: Int) : MoviePostersEntity
}

class MovieDetailsUseCaseImpl @Inject constructor(private val repo: MoviesRepo) : MovieDetailsUseCase {
    override suspend fun invoke(refreshContent: Boolean, movieId: Int) : MoviePostersEntity {
        return when (val repoResponse = repo.getMovieImagery(refresh = refreshContent, movieId = movieId)) {
            is Success -> returnData(repoResponse.data)
            is Failure -> returnError()
        }
    }

    private fun returnData(postersEntity: MoviePostersEntity) : MoviePostersEntity {
        return postersEntity
    }

    private fun returnError() = MoviePostersEntity()
}