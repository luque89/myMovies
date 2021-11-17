package com.freddyluque.data.source

import com.freddyluque.domain.Authorization
import com.freddyluque.domain.User

interface LocalDataSource {
    suspend fun getAuth(email: String): Authorization?
    suspend fun getUser(email: String): User
    suspend fun insertAuth(authorization: Authorization)
    suspend fun insertUser(user: User)
}