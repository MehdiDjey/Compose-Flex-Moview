package com.druide.flexmovies.di

import com.druide.flexmovies.data.remote.MoviesServiceRequest
import com.druide.flexmovies.data.remote.TvShowServiceRequest
import com.druide.flexmovies.data.repository.MoviesRepositoryImpl
import com.druide.flexmovies.data.repository.TvShowRepositoryImpl
import com.druide.flexmovies.domain.movies.MoviesRepository
import com.druide.flexmovies.domain.tvShow.TvShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun MoviesServiceRequest.provideMoviesRepository(): MoviesRepository {
        return MoviesRepositoryImpl(this)
    }

    @Provides
    @Singleton
    fun TvShowServiceRequest.provideTvShowRepository(): TvShowRepository {
        return TvShowRepositoryImpl(this)
    }

}