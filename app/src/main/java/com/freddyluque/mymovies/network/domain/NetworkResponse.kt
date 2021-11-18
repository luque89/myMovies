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

data class NetworkMoviesResponse(
    val movies: List<ServerMovie>,
    val routes: List<Route>
)

data class ServerMovie(
    val cast: List<Cast>,
    val categories: List<String>,
    val code: String,
    val distributor: String,
    val genre: String,
    val id: Int,
    val length: String,
    val media: List<Media>,
    val name: String,
    val original_name: String,
    val position: Int,
    val rating: String,
    val release_date: String,
    val synopsis: String
)

data class Route(
    val code: String,
    val sizes: Sizes
)

data class Cast(
    val label: String,
    val value: List<String>
)

data class Media(
    val code: String,
    val resource: String,
    val type: String
)

data class Sizes(
    val large: String?,
    val medium: String?,
    val small: String?,
)

data class NetworkTransactionResponse(
    val balance_list: List<ServerBalance>,
    val email: String,
    val level: Level,
    val name: String,
)

data class ServerBalance(
    val balance: Double,
    val key: String,
    val message: String,
    val name: String
)

data class Level(
    val advance_percent: Double,
    val key: String,
    val message: String,
    val name: String,
    val next_level: String
)