package com.freddyluque.mymovies.dataRoom.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class DatabaseUser(
    @PrimaryKey
    val email:String,
    var firstName: String,
    var lastName: String,
    var cardNumber: String
)
