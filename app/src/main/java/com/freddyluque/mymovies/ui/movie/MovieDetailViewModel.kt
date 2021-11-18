package com.freddyluque.mymovies.ui.movie

import androidx.lifecycle.ViewModel
import com.freddyluque.mymovies.MovieParcelable
import com.freddyluque.mymovies.fromMovieParcelableToDomainMovie
import com.freddyluque.usecases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MovieDetailViewModel @Inject constructor(@Named("movie") private val movieParcelable: MovieParcelable): ViewModel() {
    val movie = movieParcelable.fromMovieParcelableToDomainMovie()
}