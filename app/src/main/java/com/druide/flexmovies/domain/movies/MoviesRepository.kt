package com.druide.flexmovies.domain.movies

import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse

interface MoviesRepository {

    suspend fun getMovies(pageIndex: Int): ApiResponse<Movies>

}