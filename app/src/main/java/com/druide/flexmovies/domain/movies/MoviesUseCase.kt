package com.druide.flexmovies.domain.movies

import com.druide.flexmovies.common.Constant
import com.druide.flexmovies.domain.model.Credit
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

/**
 * Movies use case
 *
 * @property moviesRepository
 * @constructor Create empty Movies use case
 */
class MoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    /**
     * Get populars
     *
     * Get a list of the current popular movies on TMDB.
     *
     * @return
     */
    suspend fun getPopulars(): ApiResponse<Movies> {
        return moviesRepository.getPopularMovies(Constant.DEFAULT_START_PAGE)
    }

    suspend fun getPopularAt(page: Int): ApiResponse<Movies> {
        return moviesRepository.getPopularMovies(page)

    }

    /**
     * Get details
     *
     * Get the primary information about a movie.
     *
     * @param movieId
     * @return
     */
    suspend fun getDetails(movieId: Int): ApiResponse<Movie> {
        return moviesRepository.getDetailsMovie(movieId)
    }

    /**
     * Get credit
     *
     * Get the cast and crew for a movie.
     *
     * @param movieId
     * @return
     */
    suspend fun getCredit(movieId: Int): ApiResponse<Credit> {
        return moviesRepository.getCreditMovie(movieId)
    }

    /**
     * Get similar
     *
     * Get a list of similar movies.
     *
     * @param movieId
     * @return
     */
    suspend fun getSimilar(movieId: Int): ApiResponse<Movies> {
        return moviesRepository.getSimilarMovies(movieId)
    }

    /**
     * Get latest
     *
     * Get the most newly created movie.
     *
     * @return
     */
    suspend fun getLatest(): ApiResponse<Movies> {
        return moviesRepository.getLatestMovies()
    }

    /**
     * Get upcoming
     *
     * Get a list of upcoming movies in theatres.
     *
     * @return
     */
    suspend fun getUpcoming(): ApiResponse<Movies> {
        return moviesRepository.getComingMovies()
    }

    /**
     * Get top rated
     *
     * Get the top rated movies on TMDB.
     *
     * @return
     */
    suspend fun getTopRated(): ApiResponse<Movies> {
        return moviesRepository.getTopRatedMovies()
    }

    /**
     * Get now playing
     *
     * Get a list of movies in theatres.
     *
     * @return
     */
    suspend fun getNowPlaying(): ApiResponse<Movies> {
        return moviesRepository.getNowPlayingMovies()
    }

}