package com.toadfrogson.horrorhub.domain.model.movie.transformed

import com.toadfrogson.horrorhub.domain.model.movie.raw.MoviePostersEntity
import kotlinx.serialization.Serializable

data class MovieImageryData(
    val backdrops: List<MovieBackdrop>,
    val id: Int? = 0,
    val posters: List<MoviePoster>?
) {
    companion object {
        fun convertFromEntity(entity: MoviePostersEntity): MovieImageryData =
            MovieImageryData(backdrops = entity.backdrops?.map {
                MovieBackdrop(
                    filePath = it.file_path.orEmpty(),
                    height = it.height ?: 0, width = it.height ?: 0
                )
            }.orEmpty(), id = entity.id ?: 0,
                posters = entity.posters?.map {
                    MoviePoster(
                        filePath = it.file_path.orEmpty(),
                        height = it.height ?: 0,
                        width = it.height ?: 0
                    )
                })
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
