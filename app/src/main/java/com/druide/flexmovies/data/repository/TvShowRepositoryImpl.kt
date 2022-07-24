package com.druide.flexmovies.data.repository

import com.druide.flexmovies.data.remote.TvShowService
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.druide.flexmovies.domain.tvShow.TvShowRepository
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val tvShowService: TvShowService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TvShowRepository {


    override suspend fun getPopularsTvShow(): ApiResponse<Movies> {
    return withContext(dispatcher) {
        tvShowService.getPopularTvShow()
    }
    }

    override suspend fun getAllTvShow(page: Int)  : ApiResponse<Movies>{
    return withContext(dispatcher) {
        tvShowService.getAllTvShow(page)
    }
    }

    override suspend fun getTvShow(idShow: Int): ApiResponse<Movie> {
        return  withContext(dispatcher) {
            tvShowService.getTvShowById(idShow)
        }
    }
}