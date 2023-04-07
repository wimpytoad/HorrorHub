package com.toadfrogson.horrorhub.data.localData.model

import androidx.room.Embedded
import androidx.room.Relation
import com.toadfrogson.horrorhub.domain.model.movie.Genre
import com.toadfrogson.horrorhub.domain.model.movie.ProductionCompany
import com.toadfrogson.horrorhub.domain.model.movie.ProductionCountry
import com.toadfrogson.horrorhub.domain.model.movie.SpokenLanguage

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