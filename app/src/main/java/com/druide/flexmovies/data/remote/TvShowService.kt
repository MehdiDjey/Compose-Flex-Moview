package com.druide.flexmovies.data.remote

import com.druide.flexmovies.data.remote.TvRequestPAth.AIRING_TODAY
import com.druide.flexmovies.data.remote.TvRequestPAth.CREDIT
import com.druide.flexmovies.data.remote.TvRequestPAth.DETAILS
import com.druide.flexmovies.data.remote.TvRequestPAth.LATEST
import com.druide.flexmovies.data.remote.TvRequestPAth.On_THE_AIR
import com.druide.flexmovies.data.remote.TvRequestPAth.POPULAR
import com.druide.flexmovies.data.remote.TvRequestPAth.SIMILAR
import com.druide.flexmovies.data.remote.TvRequestPAth.TOP_RATED
import com.druide.flexmovies.domain.model.Credit
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowServiceRequest {


    @GET(POPULAR)
    suspend fun getPopularTvShow(@Query("page") page: Int): ApiResponse<Movies>


    @GET(DETAILS)
    suspend fun getTvShowById(@Path("idTv") id: Int): ApiResponse<Movie>


    @GET(CREDIT)
    suspend fun getCredit(idTv: Int) : ApiResponse<Credit>

    @GET(SIMILAR)
   suspend fun getSimilar(idTv: Int) : ApiResponse<Movies>

   @GET(LATEST)
   suspend fun getLatest() : ApiResponse<Movies>

   @GET(On_THE_AIR)
   suspend fun getOnAir() : ApiResponse<Movies>

   @GET(TOP_RATED)
   suspend fun getTopRated() : ApiResponse<Movies>


   @GET(AIRING_TODAY)
   suspend fun getTodayAiring() : ApiResponse<Movies>
}

private object TvRequestPAth {
    const val POPULAR = "tv/popular"

    const val DETAILS = "tv/{idTv}"

    const val CREDIT = "tv/{idTv}/credits"

    const val SIMILAR = "tv/{idTv}/similar"

    const val LATEST = "tv/latest"

    const val On_THE_AIR = "tv/on_the_air"

    const val TOP_RATED = "tv/top_rated"

    const val AIRING_TODAY = "tv/airing_today"
}