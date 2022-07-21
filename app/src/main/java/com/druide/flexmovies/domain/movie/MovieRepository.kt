package com.druide.flexmovies.domain.movie

import com.druide.flexmovies.domain.model.Movie
import com.skydoves.sandwich.ApiResponse

interface MovieRepository {
    suspend fun getMovie(idMovie: Int): ApiResponse<Movie>
}