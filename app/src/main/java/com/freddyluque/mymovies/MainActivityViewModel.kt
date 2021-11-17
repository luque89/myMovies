package com.freddyluque.mymovies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freddyluque.domain.Either
import com.freddyluque.usecases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel@Inject constructor(userUseCases: UserUseCases): ViewModel() {
    companion object {
        var email = ""
    }
    init {
//        viewModelScope.launch {
//            when(val response = userUseCases.getAuth("pruebas_beto_ia@yahoo.com","Pruebas01")){
//                is Either.Left -> {
//                    Log.i("auth1","errror: ${response.l}")
//                }
//                is Either.Right -> {
//                    when(val response = userUseCases.getUser(response.r)){
//                        is Either.Left -> {
//                            Log.i("auth1","errror user: ${response.l}")
//                        }
//                        is Either.Right -> {
//                            Log.i("auth1","cardNumber: ${response.r.cardNumber}")
//                        }
//                    }
//                }
//            }
//        }
    }
}