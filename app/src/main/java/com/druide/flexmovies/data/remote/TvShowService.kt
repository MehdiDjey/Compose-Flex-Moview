package com.druide.flexmovies.data.remote

import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowService {

    @Headers("Accept: application/json")
    @GET("discover/tv")
    suspend fun getPopularTvShow() : ApiResponse<Movies>


    @Headers("Accept: application/json")
    @GET("discover/tv")
    suspend fun getAllTvShow(@Query("page") page: Int): ApiResponse<Movies>

    @Headers("Accept: application/json")
    @GET("tv/{idMovie}")
    suspend fun getTvShowById(@Path("idMovie") idMovie: Int): ApiResponse<Movie>
}