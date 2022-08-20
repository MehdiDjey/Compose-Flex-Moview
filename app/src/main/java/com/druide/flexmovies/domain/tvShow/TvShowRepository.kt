package com.druide.flexmovies.domain.tvShow

import com.druide.flexmovies.domain.model.Credit
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse

interface TvShowRepository {

    suspend fun popular(page: Int): ApiResponse<Movies>

    suspend fun details(idTv: Int): ApiResponse<Movie>

    suspend fun credit(idTv: Int): ApiResponse<Credit>

    suspend fun similar(idTv: Int): ApiResponse<Movies>

    suspend fun latest(): ApiResponse<Movies>

    suspend fun onAir(): ApiResponse<Movies>

    suspend fun topRated(): ApiResponse<Movies>

    suspend fun todayAiring(): ApiResponse<Movies>
}