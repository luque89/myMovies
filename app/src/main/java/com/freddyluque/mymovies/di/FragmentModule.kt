package com.freddyluque.mymovies.di

import androidx.lifecycle.SavedStateHandle
import com.freddyluque.data.repository.CinemaRepository
import com.freddyluque.data.repository.UserRepository
import com.freddyluque.mymovies.MovieParcelable
import com.freddyluque.usecases.CinemaUseCases
import com.freddyluque.usecases.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.lang.IllegalStateException
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class FragmentModule {
    @Provides
    fun userProvider(userRepository: UserRepository) = UserUseCases(userRepository)

    @Provides
    fun cinemaProvider(cinemaRepository: CinemaRepository) = CinemaUseCases(cinemaRepository)

    @Provides
    @Named("movie")
    fun movieParcelable(args: SavedStateHandle): MovieParcelable = args.get<MovieParcelable>("movie")?: throw IllegalStateException("movie null")
}