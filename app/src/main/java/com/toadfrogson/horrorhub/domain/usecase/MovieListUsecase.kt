package com.toadfrogson.horrorhub.domain.usecase

import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType.CULT_CLASSIC
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieEntity
import com.toadfrogson.horrorhub.domain.model.movie.transformed.MovieUIModel
import com.toadfrogson.horrorhub.domain.repo.MoviesRepo
import com.toadfrogson.horrorhub.domain.repo.repoResult.RepoResult.Failure
import com.toadfrogson.horrorhub.domain.repo.repoResult.RepoResult.Success
import javax.inject.Inject

interface MovieListUseCase {
    suspend operator fun invoke(
        refreshContent: Boolean = false,
        listType: MovieListType = CULT_CLASSIC
    ): List<MovieUIModel>
}

class MovieListUseCaseIml @Inject constructor(private val movieRepo: MoviesRepo) : MovieListUseCase {
    override suspend operator fun invoke(refreshContent: Boolean, listType: MovieListType
    ): List<MovieUIModel> {
        val repoResponse = movieRepo.getSuggestedMovies(refresh = refreshContent, type = listType)
        return when (repoResponse) {
            is Success -> returnData(repoResponse.data)
            is Failure -> returnError()
        }
    }

    private fun returnData(movieList: List<MovieEntity>): List<MovieUIModel> {
        return movieList.map { MovieUIModel.convertFromEntity(it) }
    }

    private fun returnError() = emptyList<MovieUIModel>()
}