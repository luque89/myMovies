package com.freddyluque.domain
data class Movie(
    var id: Int,
    var name: String,
    var rating: String,
    var genre: String,
    var length: String,
    var synopsis: String,
    var resourceImage: String? = null,
    var resourceVideo: String? = null
)
