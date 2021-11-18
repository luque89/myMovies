package com.freddyluque.data.source

import com.freddyluque.domain.*

interface RemoteDataSource {
    suspend fun getAuth(email: String, password: String): Either<String, Authorization>
    suspend fun getUser(authorization: Authorization): Either<String,User>
    suspend fun getMovies(cinema: String):Either<String,List<Movie>>
    suspend fun getTransactions(cardNumber: String, pin: String): Either<String,List<Balance>>
}