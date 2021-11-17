package com.freddyluque.mymovies.di

import com.freddyluque.data.repository.UserRepository
import com.freddyluque.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun recipeRepositoryProvider(
//        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = UserRepository(remoteDataSource)
}