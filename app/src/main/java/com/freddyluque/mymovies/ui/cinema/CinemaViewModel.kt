package com.freddyluque.mymovies.ui.cinema

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freddyluque.domain.Either
import com.freddyluque.domain.Event
import com.freddyluque.domain.Movie
import com.freddyluque.mymovies.network.NETWORK_STATUS
import com.freddyluque.mymovies.ui.login.LoginViewModel
import com.freddyluque.usecases.CinemaUseCases
import com.freddyluque.usecases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CinemaViewModel @Inject constructor(private val cinemaUseCases: CinemaUseCases): ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    sealed class UiModel {
        class Network(val networkStatus: NETWORK_STATUS) : UiModel()
    }

    private val _model = MutableLiveData<Event<UiModel>>()
    val modelUi: LiveData<Event<UiModel>>
        get()  = _model

    init {
        getData()
    }

    fun getData(cinema: String = "61"){
        viewModelScope.launch {
            _model.value = Event(UiModel.Network(NETWORK_STATUS.LOADING))
            when(val response = cinemaUseCases.getMovies(cinema)){
                is Either.Left -> {
                    _model.value = Event(UiModel.Network(NETWORK_STATUS.ERROR))
                }
                is Either.Right -> {
                    _model.value = Event(UiModel.Network(NETWORK_STATUS.DONE))
                    _movieList.value = response.r!!
                }
            }
        }
    }
}