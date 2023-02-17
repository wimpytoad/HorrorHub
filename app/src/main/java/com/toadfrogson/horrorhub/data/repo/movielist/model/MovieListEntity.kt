package com.toadfrogson.horrorhub.data.repo.movielist.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieListEntity(val description: String?, val items: List<MovieEntity>?)