package com.toadfrogson.horrorhub.data.localData.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_db")
data class MovieDBEntity(
    val homepage: String?,
    @PrimaryKey
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val runtime: Int?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)


@Entity(tableName = "movie_genres")
data class MovieGenreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val movieId: Int?,
    val genreId: Int?
)

@Entity(tableName = "production_companies")
data class ProductionCompanyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val movieId: Int?,
    val logo_path: String?,
    val name: String?,
    val origin_country: String?
)

@Entity(tableName = "production_countries")
data class ProductionCountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val movieId: Int?,
    val iso_3166_1: String?,
    val name: String?
)

@Entity(tableName = "spoken_languages")
data class SpokenLanguageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val movieId: Int?,
    val english_name: String?,
    val iso_639_1: String?,
    val name: String?
)
