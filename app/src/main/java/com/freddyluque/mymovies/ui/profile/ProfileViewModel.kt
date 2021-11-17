package com.freddyluque.mymovies.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freddyluque.domain.Event
import com.freddyluque.domain.User
import com.freddyluque.mymovies.MainActivityViewModel.Companion.email

import com.freddyluque.usecases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    sealed class UiModel {
        class UserGet(val user: User) : UiModel()
    }
    private val _model = MutableLiveData<Event<UiModel>>()
    val modelUi: LiveData<Event<UiModel>>
        get()  = _model

    init {
        viewModelScope.launch {
            _model.value = Event(UiModel.UserGet(userUseCases.getUser(email)))
        }
    }
}