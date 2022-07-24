package com.druide.flexmovies.domain.tvShow

import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse

interface TvShowRepository {

    suspend fun getPopularsTvShow(): ApiResponse<Movies>

    suspend fun getAllTvShow(page: Int) : ApiResponse<Movies>

    suspend fun getTvShow(idShow: Int): ApiResponse<Movie>
}