package com.freddyluque.data.source

import com.freddyluque.domain.Authorization
import com.freddyluque.domain.Movie
import com.freddyluque.domain.User

interface LocalDataSource {
    suspend fun getAuth(email: String): Authorization?
    suspend fun getUser(email: String): User
    suspend fun insertAuth(authorization: Authorization)
    suspend fun insertUser(user: User)
    suspend fun getMovies(): List<Movie>
    suspend fun getMovie(id:String): Movie
    suspend fun insertMovies(movieList: List<Movie>)
}