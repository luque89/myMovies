package com.freddyluque.mymovies.dataRoom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.freddyluque.mymovies.dataRoom.dao.UserDao
import com.freddyluque.mymovies.dataRoom.entity.DatabaseAuthorization
import com.freddyluque.mymovies.dataRoom.entity.DatabaseUser

@Database(
    entities = [DatabaseAuthorization::class, DatabaseUser::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase: RoomDatabase() {
    abstract val userDao: UserDao

    companion object {

        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MoviesDatabase::class.java,
                        "database-movies"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}