package com.freddyluque.mymovies.di

import androidx.lifecycle.SavedStateHandle
import com.freddyluque.data.repository.UserRepository
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
}