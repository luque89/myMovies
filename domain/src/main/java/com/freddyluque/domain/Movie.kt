package com.freddyluque.domain
abstract class ResourceMovie{
    abstract var name: String
    abstract var rating: String
    abstract var genre: String
    abstract var length: String
    abstract var synopsis:String
    abstract var resourceImage: String
    abstract var resourceVideo: String
}
data class Movie(
    override var name: String,
    override var rating: String,
    override var genre: String,
    override var length: String,
    override var synopsis: String,
    override var resourceImage: String,
    override var resourceVideo: String
):ResourceMovie()
