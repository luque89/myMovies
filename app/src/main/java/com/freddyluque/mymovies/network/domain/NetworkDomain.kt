package com.freddyluque.mymovies.network.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkTransactionRequest(
    val card_number: String,
    val country_code: String = "MX",
    val pin: String,
    val transaction_include: Boolean = true
)