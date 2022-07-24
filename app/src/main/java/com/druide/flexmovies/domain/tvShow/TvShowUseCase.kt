package com.druide.flexmovies.domain.tvShow

import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class TvShowUseCase @Inject constructor ( private val tvShowRepository: TvShowRepository) {

    suspend fun getTvShowByPopularity(): ApiResponse<Movies> {
       return tvShowRepository.getPopularsTvShow()
    }

    suspend fun getAllTvShowAt(page : Int )  : ApiResponse<Movies>{
        return tvShowRepository.getAllTvShow(page)
    }

    suspend fun getTvShowDetail(idShow : Int ): ApiResponse<Movie> {
        return tvShowRepository.getTvShow(idShow)
    }
}