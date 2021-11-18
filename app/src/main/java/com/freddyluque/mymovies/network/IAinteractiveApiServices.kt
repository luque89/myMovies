package com.freddyluque.mymovies.network

import com.freddyluque.mymovies.network.domain.*
import retrofit2.http.*

const val ROOT_URL = "https://stage-api.cinepolis.com"

const val GET_AUTH = "/v2/oauth/token"
const val GET_USER = "/v1/members/profile"
const val GET_MOVIES = "/v2/movies"
const val GET_TRANSACTION = "/v2/members/loyalty"

interface IAinteractiveApiServices {
    @FormUrlEncoded
    @POST(GET_AUTH)
    suspend fun getAuth(
        @Header("api_key") apiKey: String = "stage_HNYh3RaK_Test",
        @Field("country_code") countryCode: String = "MX",
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String = "password",
        @Field("client_id") clientId: String = "IATestCandidate",
        @Field("client_secret") clientSecret: String = "c840457e777b4fee9b510fbcd4985b68",
    ): NetworkAuthResponse

    @GET(GET_USER)
    suspend fun getUser(
        @Header("Authorization") auth: String,
        @Header("api_key") apiKey: String = "stage_HNYh3RaK_Test",
        @Query("country_code") countryCode: String = "MX"
    ): NetworkUserResponse

    @GET(GET_MOVIES)
    suspend fun getMovies(
        @Header("api_key") apiKey: String = "stage_HNYh3RaK_Test",
        @Query("country_code") countryCode: String = "MX",
        @Query("cinema") cinema: String
    ): NetworkMoviesResponse

    @POST(GET_TRANSACTION)
    suspend fun getTransactions(
        @Header("api_key") apiKey: String = "stage_HNYh3RaK_Test",
        @Body networkTransactionRequest: NetworkTransactionRequest
    ): NetworkTransactionResponse
}

object IAinteractiveApi{
    val retrofitService: IAinteractiveApiServices by lazy {
        getRetrofit(ROOT_URL).create(IAinteractiveApiServices::class.java)
    }
}

enum class NETWORK_STATUS  {DONE,LOADING,ERROR}