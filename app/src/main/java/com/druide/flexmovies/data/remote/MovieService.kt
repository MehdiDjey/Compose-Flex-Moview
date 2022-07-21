package com.druide.flexmovies.data.remote

import com.druide.flexmovies.domain.model.Movie
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MovieService {

    @Headers("Accept: application/json")
    @GET("movie/{idMovie}")
    suspend fun getMovieById(@Path("idMovie") idMovie: Int): ApiResponse<Movie>
}