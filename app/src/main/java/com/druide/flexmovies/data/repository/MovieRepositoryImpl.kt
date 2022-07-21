package com.druide.flexmovies.data.repository

import com.druide.flexmovies.data.remote.MovieService
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.movie.MovieRepository
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

    override suspend fun getMovie(idMovie: Int): ApiResponse<Movie> {
        return withContext(dispatcher) {
            movieService.getMovieById(idMovie)
        }
    }
}