package com.druide.flexmovies.data.remote

import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesService {

    @Headers("Accept: application/json")
    @GET("discover/movie")
    suspend fun getPopularMovies(@Query("page") page: Int): ApiResponse<Movies>

}