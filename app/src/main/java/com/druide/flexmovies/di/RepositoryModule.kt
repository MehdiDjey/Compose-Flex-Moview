package com.druide.flexmovies.di

import com.druide.flexmovies.data.remote.MovieService
import com.druide.flexmovies.data.remote.MoviesService
import com.druide.flexmovies.data.repository.MovieRepositoryImpl
import com.druide.flexmovies.data.repository.MoviesRepositoryImpl
import com.druide.flexmovies.domain.movie.MovieRepository
import com.druide.flexmovies.domain.movies.MoviesRepository
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
    fun provideMoviesRepository(moviesService: MoviesService): MoviesRepository {
        return MoviesRepositoryImpl(moviesService)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieService: MovieService): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }
}