package com.freddyluque.usecases

import com.freddyluque.data.repository.UserRepository
import com.freddyluque.domain.Authorization
import com.freddyluque.domain.Either
import com.freddyluque.domain.User

class UserUseCases(private val userRepository: UserRepository) {
    suspend fun getAuth(email:String,password:String): Either<String, Authorization> = userRepository.getAuth(email,password)
    suspend fun getUser(authorization: Authorization): Either<String,User> = userRepository.getUser(authorization)
}