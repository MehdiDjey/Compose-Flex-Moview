package com.druide.flexmovies.domain.tvShow

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
class TvShowUseCase @Inject constructor(private val tvShowRepository: TvShowRepository) {

    /**
     * Get popular
     *
     * Get a list of the current popular TV shows on TMDB.
     *
     * @return
     */
    suspend fun getPopular(page : Int =1): ApiResponse<Movies> {
        return tvShowRepository.getPopularsTvShow(page)
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
        return tvShowRepository.getAllTvShow(page)
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
        return tvShowRepository.getTvShow(tvShowId)
    }

    /**
     * Get credit
     *
     * Get the credits (cast and crew) that have been added to a TV show.
     *
     * @param tvShowId
     * @return
     */
    suspend fun getCredit(tvShowId: Int): ApiResponse<Credit>? {
        return null
    }

    /**
     * Get similar
     *
     * Get a list of similar TV shows.
     *
     * @param tvShowId
     * @return
     */
    suspend fun getSimilar(tvShowId: Int): ApiResponse<Movies>? {
        return null
    }

    /**
     * Get latest
     *
     * Get the most newly created TV show.
     *
     * @return
     */
    suspend fun getLatest(): ApiResponse<Movies>? {
        // TODO: add repo call
        return null
    }


    /**
     * Get top rated
     *
     * Get a list of the top rated TV shows on TMDB.
     *
     * @return
     */
    suspend fun getTopRated(): ApiResponse<Movies>? {
        return null
    }


    /**
     * Get on the air
     *
     * Get a list of shows that are currently on the air.
     *
     * @return
     */
    suspend fun getOnTheAir(): Result<Movies>? {
        return null
    }

    /**
     * Get today airing
     *
     * Get a list of TV shows that are airing today.
     *
     * @return
     */
    suspend fun getTodayAiring(): Result<Movies>? {
        return null
    }

    /**
     * Get recommendations
     *
     * Get the list of TV show recommendations for this item.
     *
     *@param tvShowId
     * @return
     */
    suspend fun getRecommendations(tvShowId: Int): Result<Movies>? {
        return null
    }
}