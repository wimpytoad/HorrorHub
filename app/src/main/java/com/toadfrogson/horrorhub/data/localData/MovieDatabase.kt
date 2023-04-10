package com.toadfrogson.horrorhub.data.localData

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.toadfrogson.horrorhub.data.localData.dao.MoviesDao
import com.toadfrogson.horrorhub.data.localData.model.MovieDBEntity
import com.toadfrogson.horrorhub.data.util.EntitiesConverter


@Database(
    entities = [MovieDBEntity::class], version = 1, exportSchema = false
)
@TypeConverters(EntitiesConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}