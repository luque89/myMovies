package com.freddyluque.mymovies.network

import com.freddyluque.data.source.RemoteDataSource
import com.freddyluque.domain.Authorization
import com.freddyluque.domain.Either
import com.freddyluque.domain.User
import com.freddyluque.mymovies.network.domain.NetworkErrorResponse
import com.freddyluque.mymovies.toDomainAuth
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
                val gson = Gson()
                val type = object : TypeToken<NetworkErrorResponse>() {}.type
                val networkErrorResponse = gson.fromJson<NetworkErrorResponse>(
                    e.response()?.errorBody()!!.charStream(),
                    type
                )

                Either.Left(networkErrorResponse.message)
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
                val gson = Gson()
                val type = object : TypeToken<NetworkErrorResponse>() {}.type
                val networkErrorResponse = gson.fromJson<NetworkErrorResponse>(
                    e.response()?.errorBody()!!.charStream(),
                    type
                )

                Either.Left(networkErrorResponse.message)
            }catch (e: Exception){
                Either.Left(e.message ?: "Connection failure")
            }
        }
    }
}