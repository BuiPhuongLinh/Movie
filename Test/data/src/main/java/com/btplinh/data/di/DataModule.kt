package com.btplinh.data.di

import com.btplinh.data.remote.ApiService
import com.btplinh.data.remote.datasource.RemoteMovieSourceImpl
import com.btplinh.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideLoginRepository(apiService: ApiService): MovieRepository {
        return RemoteMovieSourceImpl(apiService)
    }
}