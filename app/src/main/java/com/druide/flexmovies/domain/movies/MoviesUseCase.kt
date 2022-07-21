package com.druide.flexmovies.domain.movies

import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend fun getPopularMovies(pageIndex: Int): ApiResponse<Movies> {
        return moviesRepository.getMovies(pageIndex)
    }

    // TODO: more uses cases
}