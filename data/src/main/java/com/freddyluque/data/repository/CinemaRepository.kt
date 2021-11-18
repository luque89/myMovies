package com.freddyluque.data.repository

import com.freddyluque.data.source.LocalDataSource
import com.freddyluque.data.source.RemoteDataSource
import com.freddyluque.domain.Balance
import com.freddyluque.domain.Either
import com.freddyluque.domain.Movie

class CinemaRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun getMovies(cinema: String): Either<String,List<Movie>>{
        return when(val response = remoteDataSource.getMovies(cinema)){
            is Either.Left -> {
                if(localDataSource.getMovies().isNullOrEmpty()){
                    Either.Left("Error al obtener la data")
                }else{
                    Either.Right(localDataSource.getMovies())
                }
            }
            is Either.Right -> {
                localDataSource.insertMovies(response.r)
                Either.Right(response.r)
            }
        }
    }
    suspend fun getTransactions(cardNumber:String,pin:String):Either<String,List<Balance>>{
        return when(val response = remoteDataSource.getTransactions(cardNumber,pin)){
            is Either.Left -> {
                Either.Left(response.l)
            }
            is Either.Right -> {
                Either.Right(response.r)
            }
        }
    }
}