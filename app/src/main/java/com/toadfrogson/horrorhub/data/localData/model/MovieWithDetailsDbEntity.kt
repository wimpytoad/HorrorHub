package com.toadfrogson.horrorhub.data.localData.model

import androidx.room.Embedded
import androidx.room.Relation
import com.toadfrogson.horrorhub.domain.model.movie.raw.Genre
import com.toadfrogson.horrorhub.domain.model.movie.raw.ProductionCompany
import com.toadfrogson.horrorhub.domain.model.movie.raw.ProductionCountry
import com.toadfrogson.horrorhub.domain.model.movie.raw.SpokenLanguage

data class MovieWithDetailsDbEntity(
    @Embedded val movie: MovieDBEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val genres: List<Genre>,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val productionCompanies: List<ProductionCompany>,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val productionCountries: List<ProductionCountry>,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val spokenLanguages: List<SpokenLanguage>
)