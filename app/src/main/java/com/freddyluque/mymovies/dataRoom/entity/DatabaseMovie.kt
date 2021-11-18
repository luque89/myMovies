package com.freddyluque.mymovies.dataRoom.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class DatabaseMovie(
    @PrimaryKey
    var id: Int,

    var name: String,
    var rating: String,
    var genre: String,
    var length: String,
    var synopsis: String,
)
