package com.freddyluque.mymovies.di

import android.app.Application
import com.freddyluque.data.source.LocalDataSource
import com.freddyluque.data.source.RemoteDataSource
import com.freddyluque.mymovies.dataRoom.db.MoviesDatabase
import com.freddyluque.mymovies.dataRoom.db.RoomDataSource
import com.freddyluque.mymovies.network.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun dataBaseProvider(app: Application) = MoviesDatabase.getInstance(app.applicationContext)

    @Provides
    fun localDataSourceProvider(db: MoviesDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = UserDataSource()
}