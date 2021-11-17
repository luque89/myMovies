package com.freddyluque.mymovies.di

import com.freddyluque.data.repository.UserRepository
import com.freddyluque.data.source.LocalDataSource
import com.freddyluque.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun userRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = UserRepository(remoteDataSource,localDataSource)
}