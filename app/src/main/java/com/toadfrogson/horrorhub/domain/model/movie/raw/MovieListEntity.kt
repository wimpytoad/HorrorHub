package com.toadfrogson.horrorhub.domain.model.movie.raw

import kotlinx.serialization.Serializable

@Serializable
data class MovieListEntity(val description: String?, val items: List<MovieEntity> = emptyList())