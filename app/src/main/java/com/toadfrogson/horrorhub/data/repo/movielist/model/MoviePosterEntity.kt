package com.toadfrogson.horrorhub.data.repo.movielist.model

import kotlinx.serialization.Serializable

@Serializable
data class MoviePostersEntity(
    val backdrops: List<Backdrop>? = null,
    val id: Int? = 0,
    val posters: List<Poster>? = null
)
@Serializable
data class Backdrop(
    val aspect_ratio: Double?,
    val file_path: String?,
    val height: Int?,
    val iso_639_1: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val width: Int?
)
@Serializable
data class Poster(
    val aspect_ratio: Double?,
    val file_path: String?,
    val height: Int?,
    val iso_639_1: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val width: Int?
)