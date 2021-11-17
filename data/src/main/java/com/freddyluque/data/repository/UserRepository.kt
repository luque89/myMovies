package com.freddyluque.data.repository

import com.freddyluque.data.source.RemoteDataSource
import com.freddyluque.domain.Authorization
import com.freddyluque.domain.Either
import com.freddyluque.domain.User

class UserRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun getAuth(email:String,password:String): Either<String,Authorization> = remoteDataSource.getAuth(email,password)
    suspend fun getUser(authorization: Authorization): Either<String,User> = remoteDataSource.getUser(authorization)
}