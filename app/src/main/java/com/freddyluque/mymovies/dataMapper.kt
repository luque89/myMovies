package com.freddyluque.mymovies

import com.freddyluque.domain.Authorization
import com.freddyluque.domain.User
import com.freddyluque.mymovies.network.domain.NetworkAuthResponse
import com.freddyluque.mymovies.network.domain.NetworkUserResponse

fun NetworkAuthResponse.toDomainAuth(password: String): Authorization{
    return Authorization(
        email = username,
        password = password,
        token_type = token_type,
        access_token = access_token
    )
}

fun NetworkUserResponse.toDomainUser(): User{
    return User(
        email,
        first_name,
        last_name,
        card_number
    )
}