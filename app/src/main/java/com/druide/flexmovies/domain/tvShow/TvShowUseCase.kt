package com.druide.flexmovies.domain.tvShow

import com.druide.flexmovies.common.Constant
import com.druide.flexmovies.domain.model.Credit
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

/**
 * Tv show use case
 *
 * @property tvShowRepository
 * @constructor Create empty Tv show use case
 */
class TvShowUseCase @Inject constructor(private val repository: TvShowRepository) {

    /**
     * Get popular
     *
     * Get a list of the current popular TV shows on TMDB.
     *
     * @return
     */
    suspend fun getPopular(): ApiResponse<Movies> {
        return repository.popular(Constant.DEFAULT_START_PAGE)
    }

    /**
     * Get all tv show at
     *
     * Get a list of the current popular TV shows on TMDB according to the page
     *
     * @param page
     * @return
     */
    suspend fun getAllTvShowAt(page: Int): ApiResponse<Movies> {
        return repository.popular(page)
    }

    /**
     * Get details
     *
     * Get the primary TV show details by id
     *
     * @param tvShowId
     * @return
     */
    suspend fun getDetails(tvShowId: Int): ApiResponse<Movie> {
        return repository.details(tvShowId)
    }

    /**
     * Get credit
     *
     * Get the credits (cast and crew) that have been added to a TV show.
     *
     * @param tvShowId
     * @return
     */
    suspend fun getCredit(tvShowId: Int): ApiResponse<Credit> {
        return repository.credit(tvShowId)
    }

    /**
     * Get similar
     *
     * Get a list of similar TV shows.
     *
     * @param tvShowId
     * @return
     */
    suspend fun getSimilar(tvShowId: Int): ApiResponse<Movies> {
        return repository.similar(tvShowId)
    }

    /**
     * Get latest
     *
     * Get the most newly created TV show.
     *
     * @return
     */
    suspend fun getLatest(): ApiResponse<Movies> {
        return repository.latest()
    }


    /**
     * Get top rated
     *
     * Get a list of the top rated TV shows on TMDB.
     *
     * @return
     */
    suspend fun getTopRated(): ApiResponse<Movies> {
        return repository.topRated()
    }


    /**
     * Get on the air
     *
     * Get a list of shows that are currently on the air.
     *
     * @return
     */
    suspend fun getOnTheAir(): ApiResponse<Movies> {
        return repository.onAir()
    }

    /**
     * Get today airing
     *
     * Get a list of TV shows that are airing today.
     *
     * @return
     */
    suspend fun getTodayAiring(): ApiResponse<Movies> {
        return repository.todayAiring()
    }

}