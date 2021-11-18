package com.freddyluque.mymovies.network

import android.util.Log
import com.freddyluque.data.source.RemoteDataSource
import com.freddyluque.domain.*
import com.freddyluque.mymovies.fromServerMovieToDomainMovieList
import com.freddyluque.mymovies.network.domain.NetworkErrorResponse
import com.freddyluque.mymovies.network.domain.NetworkTransactionRequest
import com.freddyluque.mymovies.toDomainAuth
import com.freddyluque.mymovies.toDomainBalanceList
import com.freddyluque.mymovies.toDomainUser
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class UserDataSource: RemoteDataSource {
    override suspend fun getAuth(email: String, password: String): Either<String, Authorization> {
        return withContext(Dispatchers.IO){
            try {
                val response = IAinteractiveApi.retrofitService.getAuth(username = email,password = password)
                Either.Right(response.toDomainAuth(password))
            }catch (e: HttpException){
//                val gson = Gson()
//                val type = object : TypeToken<NetworkErrorResponse>() {}.type
//                val networkErrorResponse = gson.fromJson<NetworkErrorResponse>(
//                    e.response()?.errorBody()!!.charStream(),
//                    type
//                )

                Either.Left(e.message ?: "Error en el servidor")
            }catch (e: Exception){
                Either.Left(e.message ?: "Connection failure")
            }
        }
    }

    override suspend fun getUser(authorization: Authorization): Either<String, User> {
        return withContext(Dispatchers.IO){
            val auth = "${authorization.token_type} ${authorization.access_token}"
            try {
                val response = IAinteractiveApi.retrofitService.getUser(auth = auth)
                Either.Right(response.toDomainUser())
            }catch (e: HttpException){
//                val gson = Gson()
//                val type = object : TypeToken<NetworkErrorResponse>() {}.type
//                val networkErrorResponse = gson.fromJson<NetworkErrorResponse>(
//                    e.response()?.errorBody()!!.charStream(),
//                    type
//                )

                Either.Left(e.message ?: "Error en el servidor")
            }catch (e: Exception){
                Either.Left(e.message ?: "Connection failure")
            }
        }
    }

    override suspend fun getMovies(cinema: String): Either<String, List<Movie>> {
        return withContext(Dispatchers.IO){
            try {
                val response = IAinteractiveApi.retrofitService.getMovies(cinema = cinema)
                Either.Right(response.fromServerMovieToDomainMovieList())
            }catch (e: HttpException){
//                Log.i("cines1","errorr HttpException")
//                val gson = Gson()
//                val type = object : TypeToken<NetworkErrorResponse>() {}.type
//                val networkErrorResponse = gson.fromJson<NetworkErrorResponse>(
//                    e.response()?.errorBody()!!.charStream(),
//                    type
//                )

                Either.Left(e.message ?: "Error en el servidor")
            }catch (e: Exception){
                Either.Left(e.message ?: "Connection failure")
            }
        }
    }

    override suspend fun getTransactions(
        cardNumber: String,
        pin: String
    ): Either<String, List<Balance>> {
        return withContext(Dispatchers.IO){
            try {
                val response = IAinteractiveApi.retrofitService.getTransactions(networkTransactionRequest = NetworkTransactionRequest(
                    card_number = cardNumber,
                    pin = pin
                ))
                Either.Right(response.balance_list.toDomainBalanceList())
            }catch (e: HttpException){
                Either.Left(e.message ?: "Error en el servidor")
            }catch (e: Exception){
                Either.Left(e.message ?: "Connection failure")
            }
        }
    }
}