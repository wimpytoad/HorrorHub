package com.toadfrogson.horrorhub.data.localData

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toadfrogson.horrorhub.data.localData.dao.MoviesDao
import com.toadfrogson.horrorhub.data.localData.model.MovieDBEntity
import com.toadfrogson.horrorhub.data.localData.model.MovieGenreEntity
import com.toadfrogson.horrorhub.data.localData.model.ProductionCompanyEntity
import com.toadfrogson.horrorhub.data.localData.model.ProductionCountryEntity
import com.toadfrogson.horrorhub.data.localData.model.SpokenLanguageEntity


@Database(
    entities = [MovieDBEntity::class,
        MovieGenreEntity::class,
        ProductionCompanyEntity::class,
        ProductionCountryEntity::class,
        SpokenLanguageEntity::class], version = 1, exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}