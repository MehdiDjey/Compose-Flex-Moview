package com.druide.flexmovies.data.repository

import com.druide.flexmovies.data.remote.MoviesService
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.druide.flexmovies.domain.movies.MoviesRepository
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesRepository {


    override suspend fun getPopularMovies(): ApiResponse<Movies> {
        return withContext(dispatcher) {
            moviesService.getPopularMovies()
        }
    }

    override suspend fun getAllMovies(page: Int) : ApiResponse<Movies> {
      return withContext(dispatcher) {
          moviesService.getAllMovies(page)
      }
    }

    override suspend fun getMovie(idMovie: Int): ApiResponse<Movie> {
        return withContext(dispatcher) {
            moviesService.getMovieById(idMovie)
        }
    }
}