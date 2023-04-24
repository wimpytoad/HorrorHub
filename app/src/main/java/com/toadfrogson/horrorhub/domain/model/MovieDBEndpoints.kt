package com.toadfrogson.horrorhub.domain.model

import com.toadfrogson.horrorhub.domain.model.movie.MovieListType

const val apiKey = "?api_key=8c241427387d1477f9214911ad446bbb" //TODO: move to secure place

sealed class MovieDBEndpoints {
    data class MovieListEndpoint(val type: MovieListType) : MovieDBEndpoints() {
        override fun toString() = "/3/list/${type.id}$apiKey"
    }

    data class MovieDetailsEndpoint(val movieId: Int) : MovieDBEndpoints() {
        override fun toString() = "3/movie/$movieId/images$apiKey&language=null"
    }
}