package com.freddyluque.data.source

import com.freddyluque.domain.Authorization
import com.freddyluque.domain.Either
import com.freddyluque.domain.User

interface RemoteDataSource {
    suspend fun getAuth(email: String, password: String): Either<String, Authorization>
    suspend fun getUser(authorization: Authorization): Either<String,User>
}