package com.freddyluque.mymovies.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freddyluque.domain.*
import com.freddyluque.mymovies.MainActivityViewModel.Companion.email
import com.freddyluque.mymovies.network.NETWORK_STATUS
import com.freddyluque.mymovies.ui.cinema.CinemaViewModel
import com.freddyluque.usecases.CinemaUseCases

import com.freddyluque.usecases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val cinemaUseCases: CinemaUseCases
) : ViewModel() {
    sealed class UiModel {
        class UserGet(val user: User) : UiModel()
        class Network(val networkStatus: NETWORK_STATUS,val error: String) : UiModel()
        object ShowBalance: UiModel()
    }
    private val _model = MutableLiveData<Event<UiModel>>()
    val modelUi: LiveData<Event<UiModel>>
        get()  = _model

    private val _balanceList = MutableLiveData<List<Balance>>()
    val balanceList: LiveData<List<Balance>>
        get() = _balanceList

    fun getUser(){
        viewModelScope.launch {
            _model.value = Event(UiModel.UserGet(userUseCases.getUser(email)))
        }
    }

    fun getTransaction(cardNumber:String,pin:String){
        viewModelScope.launch {
            _model.value = Event(UiModel.Network(NETWORK_STATUS.LOADING,""))
            when(val response = cinemaUseCases.getTransactions(cardNumber,pin)){
                is Either.Left -> {
                    _model.value = Event(UiModel.Network(NETWORK_STATUS.ERROR,response.l))
                }
                is Either.Right -> {
                    _model.value = Event(UiModel.Network(NETWORK_STATUS.DONE,""))
                    _balanceList.value = response.r!!
                    _model.value = Event(UiModel.ShowBalance)
                }
            }
        }
    }
}