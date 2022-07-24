package com.druide.flexmovies.di

import com.druide.flexmovies.domain.movies.MoviesRepository
import com.druide.flexmovies.domain.movies.MoviesUseCase
import com.druide.flexmovies.domain.tvShow.TvShowRepository
import com.druide.flexmovies.domain.tvShow.TvShowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun MoviesRepository.provideMoviesUseCase() = MoviesUseCase(this)

    @Provides
    @Singleton
    fun TvShowRepository.provideTvShowUseCase() = TvShowUseCase(this)
}