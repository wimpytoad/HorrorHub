package com.toadfrogson.horrorhub.domain.model.movie.transformed

import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity
import com.toadfrogson.horrorhub.domain.util.UrlUtils
import kotlinx.serialization.Serializable

data class MovieImageryData(
    val backdrops: List<MovieBackdrop> = emptyList(),
    val id: Int? = 0,
    val posters: List<MoviePoster> = emptyList()
) {
    companion object {
        fun convertFromEntity(entity: MoviePostersEntity): MovieImageryData =
            MovieImageryData(backdrops = entity.backdrops?.map {
                MovieBackdrop(
                    filePath = UrlUtils.getFullPosterUrl(it.file_path.orEmpty()),
                    height = it.height ?: 0, width = it.height ?: 0
                )
            }.orEmpty(), id = entity.id ?: 0,
                posters = entity.posters?.map {
                    MoviePoster(
                        filePath = UrlUtils.getFullPosterUrl(it.file_path.orEmpty()),
                        height = it.height ?: 0,
                        width = it.height ?: 0
                    )
                }.orEmpty()
            )
    }
}

data class MovieBackdrop(
    val filePath: String,
    val height: Int,
    val width: Int
)

@Serializable
data class MoviePoster(
    val filePath: String,
    val height: Int,
    val width: Int
)
