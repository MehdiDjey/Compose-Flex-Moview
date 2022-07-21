package com.druide.flexmovies.di

import com.druide.flexmovies.domain.movie.MovieRepository
import com.druide.flexmovies.domain.movie.MovieUseCase
import com.druide.flexmovies.domain.movies.MoviesRepository
import com.druide.flexmovies.domain.movies.MoviesUseCase
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
    fun provideMovieUseCase(movieRepository: MovieRepository) = MovieUseCase(movieRepository)

    @Provides
    @Singleton
    fun provideMoviesUseCase(moviesRepository: MoviesRepository) = MoviesUseCase(moviesRepository)
}