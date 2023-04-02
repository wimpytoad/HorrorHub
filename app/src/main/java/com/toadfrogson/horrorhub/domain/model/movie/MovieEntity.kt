package com.toadfrogson.horrorhub.domain.model.movie

import kotlinx.serialization.Serializable

@Serializable
data class MovieEntity(
    val genres: List<Genre>?,
    val homepage: String?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>?,
    val production_countries: List<ProductionCountry>?,
    val release_date: String?,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguage>?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

@Serializable
data class Genre(
    val id: Int?,
    val name: String?
)

@Serializable
data class ProductionCompany(
    val id: Int?,
    val logo_path: String?,
    val name: String?,
    val origin_country: String?
)

@Serializable
data class ProductionCountry(
    val iso_3166_1: String?,
    val name: String?
)

@Serializable
data class SpokenLanguage(
    val english_name: String?,
    val iso_639_1: String?,
    val name: String?
)