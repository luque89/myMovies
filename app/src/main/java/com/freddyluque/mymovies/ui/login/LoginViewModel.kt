package com.freddyluque.mymovies.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freddyluque.domain.Either
import com.freddyluque.domain.Event
import com.freddyluque.mymovies.MainActivityViewModel
import com.freddyluque.mymovies.network.NETWORK_STATUS
import com.freddyluque.usecases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userUseCases: UserUseCases):ViewModel() {
    sealed class UiModel {
        class Network(val networkStatus: NETWORK_STATUS,val error: String) : UiModel()
        object NavigateToProfile : UiModel()
    }

    private val _model = MutableLiveData<Event<UiModel>>()
    val modelUi: LiveData<Event<UiModel>>
        get()  = _model

    var email = ""
    var password = ""

    fun login(){
        viewModelScope.launch {
            _model.value = Event(UiModel.Network(NETWORK_STATUS.LOADING, ""))
            when(val response = userUseCases.login(email,password)){
                is Either.Left -> {
                    _model.value = Event(UiModel.Network(NETWORK_STATUS.ERROR, response.l))
                }
                is Either.Right -> {
                    _model.value = Event(UiModel.Network(NETWORK_STATUS.DONE, ""))
                    MainActivityViewModel.email = response.r.email
                    _model.value = Event(UiModel.NavigateToProfile)
                }
            }
        }
    }
}