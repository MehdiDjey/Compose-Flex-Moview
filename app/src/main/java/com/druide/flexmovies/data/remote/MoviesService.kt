package com.druide.flexmovies.data.remote

import com.druide.flexmovies.domain.model.Credit
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("discover/movie")
    suspend fun getPopularMovies(): ApiResponse<Movies>

    @GET("discover/movie")
    suspend fun getAllMovies(@Query("page") page: Int): ApiResponse<Movies>

    @GET("movie/{idMovie}")
    suspend fun getMovieById(@Path("idMovie") idMovie: Int): ApiResponse<Movie>

    @GET("movie/{idMovie}/credits")
    suspend fun getMovieCredit(@Path("idMovie") idMovie: Int): ApiResponse<Credit>

}