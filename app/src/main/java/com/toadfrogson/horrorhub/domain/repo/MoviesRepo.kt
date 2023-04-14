package com.toadfrogson.horrorhub.domain.repo

import com.toadfrogson.horrorhub.domain.model.movie.MovieListType
import com.toadfrogson.horrorhub.domain.model.movie.MovieListType.CULT_CLASSIC
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieEntity
import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.model.movie.transformed.MovieUIModel
import com.toadfrogson.horrorhub.domain.repo.repoResult.RepoResult

interface MoviesRepo {
    suspend fun getSuggestedMovies(refresh: Boolean = true, type: MovieListType = CULT_CLASSIC) : RepoResult<List<MovieEntity>>
    suspend fun getMovieImagery(refresh: Boolean = true, movieId: Int) : RepoResult<MoviePostersEntity>
}