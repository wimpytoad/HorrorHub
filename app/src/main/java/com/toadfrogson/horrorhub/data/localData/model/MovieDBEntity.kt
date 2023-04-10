package com.toadfrogson.horrorhub.data.localData.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toadfrogson.horrorhub.domain.model.movie.transformed.MovieUIModel

@Entity(tableName = "movies_db")
data class MovieDBEntity(
    @PrimaryKey val id: Int,
    val homepage: String,
    val originalLanguage: String,
    val originalTitle: String,
    val title: String, //whats the difference with originalTItle?
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
        fun convertFromUiModel(model: MovieUIModel): MovieDBEntity =
            MovieDBEntity(
                id = model.id,
                homepage = model.homepage,
                originalTitle = model.homepage,
                title = model.title,
                originalLanguage = model.originalLanguage,
                genres = model.genres,
                overview = model.overview,
                posterUrl = model.posterUrl,
                popularity = model.popularity,
                countries = model.countries,
                runtime = model.runtime,
                releaseDate = model.releaseDate,
                tagline = model.tagline,
                hasTrailer = model.hasTrailer,
                voteAverage = model.voteAverage,
                voteCount = model.voteCount
            )

        fun convertToUiModel(entity: MovieDBEntity): MovieUIModel =
            MovieUIModel(
                id = entity.id,
                homepage = entity.homepage,
                originalTitle = entity.homepage,
                title = entity.title,
                originalLanguage = entity.originalLanguage,
                genres = entity.genres,
                overview = entity.overview,
                posterUrl = entity.posterUrl,
                popularity = entity.popularity,
                countries = entity.countries,
                runtime = entity.runtime,
                releaseDate = entity.releaseDate,
                tagline = entity.tagline,
                hasTrailer = entity.hasTrailer,
                voteAverage = entity.voteAverage,
                voteCount = entity.voteCount
            )
    }
}
