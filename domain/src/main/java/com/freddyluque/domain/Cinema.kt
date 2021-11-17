package com.freddyluque.domain
abstract class ResourceCinema{
    abstract var name: String
    abstract var phone: String
}

data class Cinema(
    override var name: String,
    override var phone: String
): ResourceCinema()
