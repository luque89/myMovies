package com.freddyluque.mymovies.network.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkErrorResponse(
    val status_code: String,
    val message: String
)

data class NetworkAuthResponse(
    val access_token: String,
    val country_code: String,
    val expires_in: Int,
    val refresh_token: String,
    val token_type: String,
    val username: String,
//    @SerializedName(".expires")
//    @Expose
//    val expire: String
)

data class NetworkUserResponse(
    val card_number: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val phone_number: String,
    val profile_picture: String
)