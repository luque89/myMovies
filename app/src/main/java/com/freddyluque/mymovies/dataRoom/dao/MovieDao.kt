package com.freddyluque.mymovies.dataRoom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.freddyluque.mymovies.dataRoom.entity.DatabaseMovie

@Dao
interface MovieDao {
    @Query("SELECT * from movie WHERE id = :id")
    suspend fun getMovie(id:String): DatabaseMovie

    @Query("SELECT * from movie")
    suspend fun getMovies(): List<DatabaseMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movieList: List<DatabaseMovie>)
}