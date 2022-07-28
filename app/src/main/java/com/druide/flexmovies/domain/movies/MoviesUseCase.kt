package com.druide.flexmovies.domain.movies

import com.druide.flexmovies.domain.model.Credit
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
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

    suspend fun getMovieCredit(movieId: Int): ApiResponse<Credit> {
        return moviesRepository.getCredit(movieId)
    }

}