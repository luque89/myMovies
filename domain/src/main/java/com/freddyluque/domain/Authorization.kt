package com.freddyluque.domain

data class Authorization(
        var email: String,
        var password: String,
        var token_type: String,
        var access_token: String
)