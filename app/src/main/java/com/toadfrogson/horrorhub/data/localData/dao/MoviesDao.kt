package com.toadfrogson.horrorhub.data.localData.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion
import androidx.room.Query
import com.toadfrogson.horrorhub.data.localData.model.MovieDBEntity

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies_db")
    fun getAll() : List<MovieDBEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieDBEntity>)

    @Query("SELECT * FROM movies_db WHERE id IN (:movieId)")
    fun getById(movieId: Int): MovieDBEntity

}