package com.toadfrogson.horrorhub.domain.model.movie.transformed

import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieEntity

data class MovieUIModel(
    val id: Int,
    val homepage: String,
    val originalLanguage: String,
    val originalTitle: String,
    val title: String,
    val genres: List<String>,
    val overview: String,
    val popularity: Double,
    val posterUrl: String,
    val countries: List<String>,
    val releaseDate: String,
    val runtime: Int,
    val tagline: String,
    val hasTrailer: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
) {
    companion object {
        fun convertFromEntity(entity: MovieEntity) =
            MovieUIModel(
                id = entity.id ?: -1,
                homepage = entity.homepage.orEmpty(),
                originalLanguage = entity.original_language.orEmpty(),
                originalTitle = entity.original_title.orEmpty(),
                title = entity.title.orEmpty(),
                genres = entity.genres?.mapNotNull { it.name }.orEmpty(),
                overview = entity.overview.orEmpty(),
                popularity = entity.popularity ?: 0.0,
                posterUrl = entity.poster_path.orEmpty(),
                countries = entity.production_countries?.mapNotNull { it.name }.orEmpty(),
                releaseDate = entity.release_date.orEmpty(),
                runtime = entity.runtime ?: -1,
                tagline = entity.tagline.orEmpty(),
                hasTrailer = entity.video ?: false,
                voteAverage = entity.vote_average ?: 0.0,
                voteCount = entity.vote_count ?: -1
            )
    }
}