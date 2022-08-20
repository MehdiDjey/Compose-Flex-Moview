package com.druide.flexmovies.domain.movies

import com.druide.flexmovies.domain.model.Credit
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): ApiResponse<Movies>

    suspend fun getDetailsMovie(idMovie: Int): ApiResponse<Movie>

    suspend fun getCreditMovie(idMovie: Int): ApiResponse<Credit>

    suspend fun getSimilarMovies(idMovie: Int): ApiResponse<Movies>

    suspend fun getLatestMovies(): ApiResponse<Movies>

    suspend fun getComingMovies(): ApiResponse<Movies>

    suspend fun getTopRatedMovies(): ApiResponse<Movies>

    suspend fun getNowPlayingMovies(): ApiResponse<Movies>

}