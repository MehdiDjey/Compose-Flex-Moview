package com.druide.flexmovies.di

import com.druide.flexmovies.data.remote.MoviesServiceRequest
import com.druide.flexmovies.data.remote.TvShowService
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
    fun Retrofit.provideMoviesService () : MoviesServiceRequest =
        this.create(MoviesServiceRequest::class.java)

    @Singleton
    @Provides
    fun Retrofit.provideTvShowService() : TvShowService =
        this.create(TvShowService::class.java)

}