package com.druide.flexmovies.di

import com.druide.flexmovies.data.remote.MovieService
import com.druide.flexmovies.data.remote.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Singleton
    @Provides
    fun provideMoviesService(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}