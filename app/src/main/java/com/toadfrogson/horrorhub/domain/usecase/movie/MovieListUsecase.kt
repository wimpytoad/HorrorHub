package com.toadfrogson.horrorhub.domain.usecase.movie

import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType.CULT_CLASSIC
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieEntity
import com.toadfrogson.horrorhub.domain.model.movie.transformed.MovieUIModel
import com.toadfrogson.horrorhub.domain.repo.MoviesRepo
import com.toadfrogson.horrorhub.domain.repo.repoResult.RepoResult.Failure
import com.toadfrogson.horrorhub.domain.repo.repoResult.RepoResult.Success
import com.toadfrogson.horrorhub.domain.usecase.UseCase
import javax.inject.Inject

interface MovieListUseCase : UseCase<MovieListUseCase.Params, List<MovieUIModel>> {
    override suspend operator fun invoke(
        params: Params
    ): List<MovieUIModel>

    data class Params(
        val refreshContent: Boolean = false,
        val listType: MovieListType = CULT_CLASSIC
    )
}

class MovieListUseCaseImpl @Inject constructor(private val repo: MoviesRepo) : MovieListUseCase {
    override suspend operator fun invoke(
        params: MovieListUseCase.Params
    ): List<MovieUIModel> {
        return when (val repoResponse =
            repo.getSuggestedMovies(refresh = params.refreshContent, type = params.listType)) {
            is Success -> returnData(repoResponse.data)
            is Failure -> returnError()
        }
    }

    private fun returnData(movieList: List<MovieEntity>): List<MovieUIModel> {
        return movieList.map { MovieUIModel.convertFromEntity(it) }
    }

    private fun returnError() = emptyList<MovieUIModel>()

}