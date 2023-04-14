package com.toadfrogson.horrorhub.data.localData.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.toadfrogson.horrorhub.data.localData.converters.GenreListTypeConverter
import com.toadfrogson.horrorhub.data.localData.converters.ProductionCompanyListTypeConverter
import com.toadfrogson.horrorhub.data.localData.converters.ProductionCountryListTypeConverter
import com.toadfrogson.horrorhub.data.localData.converters.SpokenLanguageListTypeConverter
import com.toadfrogson.horrorhub.domain.model.movie.raw.Genre
import com.toadfrogson.horrorhub.domain.model.movie.raw.MovieEntity
import com.toadfrogson.horrorhub.domain.model.movie.raw.ProductionCompany
import com.toadfrogson.horrorhub.domain.model.movie.raw.ProductionCountry
import com.toadfrogson.horrorhub.domain.model.movie.raw.SpokenLanguage

@Entity(tableName = "movies_db")
data class MovieDbEntity(
    @PrimaryKey val id: Int?,
    val homepage: String?,
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
    val vote_count: Int?,
    @TypeConverters(GenreListTypeConverter::class) val genres: List<Genre>?,
    @TypeConverters(ProductionCompanyListTypeConverter::class) val production_companies: List<ProductionCompany>?,
    @TypeConverters(ProductionCountryListTypeConverter::class) val production_countries: List<ProductionCountry>?,
    @TypeConverters(SpokenLanguageListTypeConverter::class) val spoken_languages: List<SpokenLanguage>?
) {

    constructor(movieEntity: MovieEntity) : this(
        id = movieEntity.id,
        homepage = movieEntity.homepage,
        original_language = movieEntity.original_language,
        original_title = movieEntity.original_title,
        overview = movieEntity.overview,
        popularity = movieEntity.popularity,
        poster_path = movieEntity.poster_path,
        release_date = movieEntity.release_date,
        runtime = movieEntity.runtime,
        tagline = movieEntity.tagline,
        title = movieEntity.title,
        video = movieEntity.video,
        vote_average = movieEntity.vote_average,
        vote_count = movieEntity.vote_count,
        genres = movieEntity.genres,
        production_companies = movieEntity.production_companies,
        production_countries = movieEntity.production_countries,
        spoken_languages = movieEntity.spoken_languages
    )

    companion object {
        fun toMovieEntity(dbEntityDb2: MovieDbEntity): MovieEntity {
            return MovieEntity(
                id = dbEntityDb2.id,
                homepage = dbEntityDb2.homepage,
                original_language = dbEntityDb2.original_language,
                original_title = dbEntityDb2.original_title,
                overview = dbEntityDb2.overview,
                popularity = dbEntityDb2.popularity,
                poster_path = dbEntityDb2.poster_path,
                release_date = dbEntityDb2.release_date,
                runtime = dbEntityDb2.runtime,
                tagline = dbEntityDb2.tagline,
                title = dbEntityDb2.title,
                video = dbEntityDb2.video,
                vote_average = dbEntityDb2.vote_average,
                vote_count = dbEntityDb2.vote_count,
                genres = dbEntityDb2.genres,
                production_companies = dbEntityDb2.production_companies,
                production_countries = dbEntityDb2.production_countries,
                spoken_languages = dbEntityDb2.spoken_languages
            )
        }
    }
}