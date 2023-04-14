package com.toadfrogson.horrorhub.data.localData

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.toadfrogson.horrorhub.data.localData.converters.GenreListTypeConverter
import com.toadfrogson.horrorhub.data.localData.converters.ProductionCompanyListTypeConverter
import com.toadfrogson.horrorhub.data.localData.converters.ProductionCountryListTypeConverter
import com.toadfrogson.horrorhub.data.localData.converters.SpokenLanguageListTypeConverter
import com.toadfrogson.horrorhub.data.localData.dao.MoviesDao
import com.toadfrogson.horrorhub.data.localData.model.MovieDbEntity


@Database(
    entities = [MovieDbEntity::class], version = 1, exportSchema = false
)
@TypeConverters(
    ProductionCompanyListTypeConverter::class,
    ProductionCountryListTypeConverter::class,
    SpokenLanguageListTypeConverter::class,
    GenreListTypeConverter::class
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}