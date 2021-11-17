package com.freddyluque.mymovies.dataRoom.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authorization")
data class DatabaseAuthorization(
    @PrimaryKey
    var email: String,

    var password: String,
    var token_type: String,
    var access_token: String
)
