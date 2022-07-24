package com.druide.flexmovies.data.remote

import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @Headers("Accept: application/json")
    @GET("discover/movie")
    suspend fun getPopularMovies(): ApiResponse<Movies>


    @Headers("Accept: application/json")
    @GET("discover/movie")
    suspend fun getAllMovies(@Query("page") page: Int): ApiResponse<Movies>

    @Headers("Accept: application/json")
    @GET("movie/{idMovie}")
    suspend fun getMovieById(@Path("idMovie") idMovie: Int): ApiResponse<Movie>

}