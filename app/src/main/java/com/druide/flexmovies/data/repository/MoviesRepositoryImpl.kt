package com.druide.flexmovies.data.repository

import com.druide.flexmovies.data.remote.MoviesServiceRequest
import com.druide.flexmovies.domain.model.Credit
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.druide.flexmovies.domain.movies.MoviesRepository
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesServiceRequest: MoviesServiceRequest,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): ApiResponse<Movies> {
        return withContext(dispatcher) {
            moviesServiceRequest.getPopular(page)
        }
    }

    override suspend fun getDetailsMovie(idMovie: Int): ApiResponse<Movie> {
        return withContext(dispatcher) {
            moviesServiceRequest.getMovie(idMovie)
        }
    }

    override suspend fun getCreditMovie(idMovie: Int): ApiResponse<Credit> {
        return withContext(dispatcher) {
            moviesServiceRequest.getCredit(idMovie)
        }
    }

    override suspend fun getSimilarMovies(idMovie: Int): ApiResponse<Movies> {
        return withContext(dispatcher) {
            moviesServiceRequest.getSimilar(idMovie)
        }
    }

    override suspend fun getLatestMovies(): ApiResponse<Movies> {
        return withContext(dispatcher) {
            moviesServiceRequest.getLatest()
        }
    }

    override suspend fun getComingMovies(): ApiResponse<Movies> {
        return withContext(dispatcher) {
            moviesServiceRequest.getUpComing()
        }
    }

    override suspend fun getTopRatedMovies(): ApiResponse<Movies> {
        return withContext(dispatcher) {
            moviesServiceRequest.getTopRated()
        }
    }

    override suspend fun getNowPlayingMovies(): ApiResponse<Movies> {
        return withContext(dispatcher) {
            moviesServiceRequest.getNowPlaying()
        }
    }
}