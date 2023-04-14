package com.toadfrogson.horrorhub.data.localData.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toadfrogson.horrorhub.data.localData.model.MovieDbEntity

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies_db")
    fun getAll() : List<MovieDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieDbEntity>)

    @Query("SELECT * FROM movies_db WHERE id IN (:movieId)")
    fun getById(movieId: Int): MovieDbEntity

}