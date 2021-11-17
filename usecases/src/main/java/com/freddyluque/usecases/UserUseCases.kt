package com.freddyluque.usecases

import com.freddyluque.data.repository.UserRepository
import com.freddyluque.domain.Authorization
import com.freddyluque.domain.Either
import com.freddyluque.domain.User

class UserUseCases(private val userRepository: UserRepository) {
    suspend fun login(email:String,password:String): Either<String, User> = userRepository.login(email,password)
    suspend fun getUser(email: String): User = userRepository.getUser(email)
}