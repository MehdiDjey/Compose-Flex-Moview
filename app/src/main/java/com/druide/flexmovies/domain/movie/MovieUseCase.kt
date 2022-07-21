package com.druide.flexmovies.domain.movie

import com.druide.flexmovies.domain.model.Movie
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun getMovieById(movieId: Int): ApiResponse<Movie> {
        return movieRepository.getMovie(movieId)
    }
}