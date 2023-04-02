package com.toadfrogson.horrorhub.domain.model.movie

import kotlinx.serialization.Serializable

@Serializable
data class MovieListEntity(val description: String?, val items: List<MovieEntity>?)