package com.toadfrogson.horrorhub.domain.usecase.movie

import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResult
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieEntity
import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.repo.MoviesRepo
import com.toadfrogson.horrorhub.domain.usecase.ErrorType
import com.toadfrogson.horrorhub.domain.usecase.UseCase
import com.toadfrogson.horrorhub.domain.usecase.UseCaseError
import com.toadfrogson.horrorhub.domain.usecase.UseCaseResult
import com.toadfrogson.horrorhub.domain.usecase.UseCaseResult.Failure
import com.toadfrogson.horrorhub.domain.usecase.UseCaseResult.Success
import com.toadfrogson.horrorhub.domain.util.UrlUtils
import javax.inject.Inject
import kotlin.random.Random

interface MovieGuessGameUseCase : UseCase<String, UseCaseResult<GuessMovieDataSet>> {
    override suspend operator fun invoke(params: String): UseCaseResult<GuessMovieDataSet>
}

class MovieGuessGameUseCaseImpl @Inject constructor(private val repo: MoviesRepo) :
    MovieGuessGameUseCase {
    override suspend operator fun invoke(params: String): UseCaseResult<GuessMovieDataSet> {
        return when (val result = repo.getSuggestedMovies(refresh = false)) {
            is ApiResult.Success -> getDataSet(result.data)
            is ApiResult.Failure -> Failure(UseCaseError(ErrorType.NoContent))
        }
    }

    private suspend fun getDataSet(movies: List<MovieEntity>): UseCaseResult<GuessMovieDataSet> {
        val gameList = movies.shuffled().take(4)
        val movieIdToGuess = Random.nextInt(4)
        val movieToGuess = gameList[movieIdToGuess]
        return when (val imageryResult =
            repo.getMovieImagery(refresh = true, movieToGuess.id ?: 0)) {
            is ApiResult.Success -> {
                Success(
                    GuessMovieDataSet.map(
                        answers = gameList.mapNotNull { it.title },
                        imagery = imageryResult.data,
                        correctAnswer = movieIdToGuess
                    )
                )
            }

            is ApiResult.Failure -> Failure(UseCaseError(ErrorType.NoContent))
        }
    }
}

data class GuessMovieDataSet(
    val movieScreens: List<String> = emptyList(),
    val answers: List<String> = emptyList(),
    val correctAnswer: Int = 0
) {
    companion object {
        fun map(
            answers: List<String>,
            imagery: MoviePostersEntity,
            correctAnswer: Int
        ): GuessMovieDataSet {
            return GuessMovieDataSet(
                movieScreens = imagery.backdrops?.take(4)
                    ?.map { UrlUtils.getFullPosterUrl(it.file_path.orEmpty()) }
                    .orEmpty(),
                answers = answers, correctAnswer = correctAnswer
            )
        }
    }
}