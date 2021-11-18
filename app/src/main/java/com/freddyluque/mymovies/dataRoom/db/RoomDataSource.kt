package com.freddyluque.mymovies.dataRoom.db

import com.freddyluque.data.source.LocalDataSource
import com.freddyluque.domain.Authorization
import com.freddyluque.domain.Movie
import com.freddyluque.domain.User
import com.freddyluque.mymovies.*

class RoomDataSource(db: MoviesDatabase): LocalDataSource{
    private val userDao = db.userDao
    private val movieDao = db.movieDao

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

    override suspend fun getMovies(): List<Movie> {
        return movieDao.getMovies().toDomainMovieList()
    }

    override suspend fun getMovie(id: String): Movie {
        return movieDao.getMovie(id).toDomain()
    }

    override suspend fun insertMovies(movieList: List<Movie>) {
        movieDao.insertMovies(movieList.toDatabaseMovieList())
    }
}
