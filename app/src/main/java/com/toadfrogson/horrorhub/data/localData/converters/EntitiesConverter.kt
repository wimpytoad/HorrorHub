package com.toadfrogson.horrorhub.data.localData.converters

import androidx.room.TypeConverter
import com.toadfrogson.horrorhub.domain.model.movie.raw.Genre
import com.toadfrogson.horrorhub.domain.model.movie.raw.ProductionCompany
import com.toadfrogson.horrorhub.domain.model.movie.raw.ProductionCountry
import com.toadfrogson.horrorhub.domain.model.movie.raw.SpokenLanguage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val json = Json { ignoreUnknownKeys = true }

//TODO: lots of repeating code, generalise this
object ProductionCompanyListTypeConverter {

    @TypeConverter
    fun fromProductionCompanyList(
        companies: List<ProductionCompany>?
    ): String {
        return json.encodeToString(companies)
    }

    @TypeConverter
    fun toProductionCompanyList(companiesJson: String?): List<ProductionCompany>? {
        return json.decodeFromString(companiesJson.orEmpty())
    }
}

object ProductionCountryListTypeConverter {

    @TypeConverter
    fun fromProductionCountryList(countries: List<ProductionCountry>?): String {
        return json.encodeToString(countries)
    }

    @TypeConverter
    fun toProductionCountryList(countriesJson: String?): List<ProductionCountry>? {
        return json.decodeFromString(countriesJson.orEmpty())
    }
}

object SpokenLanguageListTypeConverter {

    @TypeConverter
    fun fromSpokenLanguageList(languages: List<SpokenLanguage>?): String {
        return json.encodeToString(languages)
    }

    @TypeConverter
    fun toSpokenLanguageList(languagesJson: String?): List<SpokenLanguage>? {
        return json.decodeFromString(languagesJson.orEmpty())
    }
}

object GenreListTypeConverter {
    @TypeConverter
    fun fromGenreList(genres: List<Genre>?): String {
        return json.encodeToString(genres)
    }

    @TypeConverter
    fun toGenreList(genresJson: String?): List<Genre>? {
        return json.decodeFromString(genresJson.orEmpty())
    }
}