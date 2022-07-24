package com.druide.flexmovies.domain.movies

import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend fun getMoviesByPopularity(): ApiResponse<Movies> {
        return moviesRepository.getPopularMovies()
    }

    suspend fun getAllMoviesAt(page : Int) : ApiResponse<Movies> {
        return  moviesRepository.getAllMovies(page)
    }

    suspend fun getMovieDetails(movieId: Int): ApiResponse<Movie> {
        return moviesRepository.getMovie(movieId)
    }

}