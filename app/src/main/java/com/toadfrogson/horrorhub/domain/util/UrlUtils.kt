package com.toadfrogson.horrorhub.domain.util

object UrlUtils {
    fun getFullPosterUrl(posterPath: String): String {
        return "https://image.tmdb.org/t/p/original/" + posterPath
    }
}