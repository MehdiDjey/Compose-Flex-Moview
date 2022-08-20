package com.druide.flexmovies.data.repository


import com.druide.flexmovies.data.remote.TvShowServiceRequest
import com.druide.flexmovies.domain.model.Credit
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.druide.flexmovies.domain.tvShow.TvShowRepository
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val service: TvShowServiceRequest,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TvShowRepository {

    override suspend fun popular(page: Int): ApiResponse<Movies> {
        return  withContext(dispatcher) {
            service.getPopularTvShow(page)
        }
    }

    override suspend fun details(idTv: Int): ApiResponse<Movie> {
        return  withContext(dispatcher) {
            service.getTvShowById(idTv)
        }
    }

    override suspend fun credit(idTv: Int): ApiResponse<Credit> {
     return  withContext(dispatcher) {
         service.getCredit(idTv)
     }
    }

    override suspend fun similar(idTv: Int): ApiResponse<Movies> {
        return  withContext(dispatcher) {
            service.getSimilar(idTv)
        }
    }

    override suspend fun latest(): ApiResponse<Movies> {
        return  withContext(dispatcher) {
            service.getLatest()
        }
    }

    override suspend fun onAir(): ApiResponse<Movies> {
        return  withContext(dispatcher) {
            service.getOnAir()
        }
    }

    override suspend fun topRated(): ApiResponse<Movies> {
        return  withContext(dispatcher) {
            service.getTopRated()
        }
    }


    override suspend fun todayAiring(): ApiResponse<Movies> {
        return  withContext(dispatcher) {
            service.getTodayAiring()
        }
    }
}