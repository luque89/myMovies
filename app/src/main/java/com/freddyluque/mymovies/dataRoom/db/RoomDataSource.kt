package com.freddyluque.mymovies.dataRoom.db

import com.freddyluque.data.source.LocalDataSource
import com.freddyluque.domain.Authorization
import com.freddyluque.domain.User
import com.freddyluque.mymovies.toDatabaseAuthorization
import com.freddyluque.mymovies.toDatabaseUser
import com.freddyluque.mymovies.toDomainAuthorization
import com.freddyluque.mymovies.toDomainUser

class RoomDataSource(db: MoviesDatabase): LocalDataSource{
    private val userDao = db.userDao

    override suspend fun getAuth(email: String): Authorization? {
        return userDao.getAuthorization(email)?.toDomainAuthorization()
    }

    override suspend fun getUser(email: String): User {
        return userDao.getUser(email).toDomainUser()
    }

    override suspend fun insertAuth(authorization: Authorization) {
        userDao.insertOrUpdateAuthorization(authorization.toDatabaseAuthorization())
    }

    override suspend fun insertUser(user: User) {
        userDao.insertOrUpdateUser(user.toDatabaseUser())
    }

}
