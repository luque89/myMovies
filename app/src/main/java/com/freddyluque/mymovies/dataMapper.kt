package com.freddyluque.mymovies

import android.os.Parcelable
import com.freddyluque.domain.Authorization
import com.freddyluque.domain.Balance
import com.freddyluque.domain.Movie
import com.freddyluque.domain.User
import com.freddyluque.mymovies.dataRoom.entity.DatabaseAuthorization
import com.freddyluque.mymovies.dataRoom.entity.DatabaseMovie
import com.freddyluque.mymovies.dataRoom.entity.DatabaseUser
import com.freddyluque.mymovies.network.domain.*
import kotlinx.android.parcel.Parcelize

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

fun DatabaseAuthorization.toDomainAuthorization(): Authorization{
    return Authorization(email, password, token_type, access_token)
}

fun DatabaseUser.toDomainUser(): User{
    return User(email, firstName, lastName, cardNumber)
}

fun Authorization.toDatabaseAuthorization(): DatabaseAuthorization{
    return DatabaseAuthorization(email, password, token_type, access_token)
}

fun User.toDatabaseUser(): DatabaseUser{
    return DatabaseUser(email, firstName, lastName, cardNumber)
}

fun List<DatabaseMovie>.toDomainMovieList(): List<Movie>{
    return map{
        Movie(
            name = it.name,
            rating = it.rating,
            genre = it.genre,
            length = it.length,
            synopsis = it.synopsis,
            id = it.id
        )
    }
}

fun DatabaseMovie.toDomain(): Movie{
    return Movie(
        name = name,
        rating = rating,
        genre = genre,
        length = length,
        synopsis = synopsis,
        id = id
    )
}

fun List<Movie>.toDatabaseMovieList(): List<DatabaseMovie>{
    return map {
        DatabaseMovie(
            name = it.name,
            rating = it.rating,
            genre = it.genre,
            length = it.length,
            synopsis = it.synopsis,
            id = it.id
        )
    }
}

fun NetworkMoviesResponse.fromServerMovieToDomainMovieList():List<Movie>{
    val movieList = this.movies.map {
        Movie(
            id = it.id,
            name = it.name,
            rating = it.rating,
            genre = it.genre,
            length = it.length,
            synopsis = it.synopsis,
            resourceImage = this.routes.find {it.code == "poster"}?.let { route ->
                "${route.sizes.large}${it.media.find { it.type == "image" && it.code == "poster" }?.resource}"
            },
            resourceVideo = this.routes.find { it.code == "trailer_mp4" }?.let { route ->
                "${route.sizes.medium}${it.media.find{it.type == "video"}?.resource}"
            }
        )
    }
    return movieList
}

@Parcelize
class MovieParcelable(
    var id: Int,
    var name: String,
    var rating: String,
    var genre: String,
    var length: String,
    var synopsis: String,
    var resourceImage: String? = null,
    var resourceVideo: String? = null
): Parcelable

fun Movie.toMovieParcelable():MovieParcelable{
    return MovieParcelable(id, name, rating, genre, length, synopsis, resourceImage, resourceVideo)
}

fun MovieParcelable.fromMovieParcelableToDomainMovie():Movie{
    return Movie(id, name, rating, genre, length, synopsis, resourceImage, resourceVideo)
}

fun List<ServerBalance>.toDomainBalanceList(): List<Balance>{
    return map {
        Balance(
            balance = it.balance,
            key = it.key,
            message = it.message,
            name = it.name
        )
    }
}