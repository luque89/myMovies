package com.freddyluque.mymovies.dataRoom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.freddyluque.mymovies.dataRoom.entity.DatabaseAuthorization
import com.freddyluque.mymovies.dataRoom.entity.DatabaseUser

@Dao
interface UserDao {
    @Query("SELECT * from user WHERE email = :email")
    suspend fun getUser(email:String): DatabaseUser

    @Query("SELECT * from authorization WHERE email = :email")
    suspend fun getAuthorization(email:String): DatabaseAuthorization?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user: DatabaseUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAuthorization(authorization: DatabaseAuthorization)
}