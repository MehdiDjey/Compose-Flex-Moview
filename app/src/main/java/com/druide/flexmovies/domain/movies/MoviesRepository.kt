package com.druide.flexmovies.domain.movies

import com.druide.flexmovies.domain.model.Credit
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse

interface MoviesRepository {

    suspend fun getPopularMovies(): ApiResponse<Movies>

    suspend fun getAllMovies(page: Int) : ApiResponse<Movies>

    suspend fun getMovie(idMovie: Int): ApiResponse<Movie>

    suspend fun getCredit(idMovie: Int) : ApiResponse<Credit>

}