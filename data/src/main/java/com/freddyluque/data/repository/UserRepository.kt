package com.freddyluque.data.repository

import com.freddyluque.data.source.LocalDataSource
import com.freddyluque.data.source.RemoteDataSource
import com.freddyluque.domain.Authorization
import com.freddyluque.domain.Either
import com.freddyluque.domain.User

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    //suspend fun getAuth(email:String,password:String): Either<String,Authorization> = remoteDataSource.getAuth(email,password)
    suspend fun getUser(email:String): User = localDataSource.getUser(email)
    suspend fun login(email:String,password:String): Either<String,User> {
        if(localDataSource.getAuth(email) == null){
            when(val auth = remoteDataSource.getAuth(email,password)){
                is Either.Left -> {
                    return Either.Left(auth.l)
                }
                is Either.Right -> {
                    when(val user = remoteDataSource.getUser(auth.r)){
                        is Either.Left -> {
                            return Either.Left(user.l)
                        }
                        is Either.Right -> {
                            localDataSource.insertAuth(auth.r)
                            localDataSource.insertUser(user.r)
                        }
                    }
                }
            }
        }
        return Either.Right(localDataSource.getUser(email))
    }
}