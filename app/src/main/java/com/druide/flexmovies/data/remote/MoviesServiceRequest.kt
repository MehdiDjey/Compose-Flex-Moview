package com.druide.flexmovies.data.remote

import com.druide.flexmovies.data.remote.MovieRequestPath.CREDIT
import com.druide.flexmovies.data.remote.MovieRequestPath.DETAILS
import com.druide.flexmovies.data.remote.MovieRequestPath.LATEST
import com.druide.flexmovies.data.remote.MovieRequestPath.NOW_PLAYING
import com.druide.flexmovies.data.remote.MovieRequestPath.POPULAR
import com.druide.flexmovies.data.remote.MovieRequestPath.SIMILAR
import com.druide.flexmovies.data.remote.MovieRequestPath.TOP_RATED
import com.druide.flexmovies.data.remote.MovieRequestPath.UP_COMING
import com.druide.flexmovies.domain.model.Credit
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesServiceRequest {


    @GET(POPULAR)
    suspend fun getPopularMovies(@Query("page") page: Int): ApiResponse<Movies>

    @GET(DETAILS)
    suspend fun getMovieById(@Path("idMovie") idMovie: Int): ApiResponse<Movie>

    @GET(CREDIT)
    suspend fun getCreditMovie(@Path("idMovie") idMovie: Int): ApiResponse<Credit>


    @GET(SIMILAR)
    suspend fun getSimilarMovies(@Path("idMovie") idMovie: Int) : ApiResponse<Movies>

    @GET(LATEST)
    suspend fun getLatestMovies() : ApiResponse<Movies>

    @GET(UP_COMING)
    suspend fun getUpComingMovies(): ApiResponse<Movies>

    @GET(TOP_RATED)
    suspend fun getTopRatedMovies() : ApiResponse<Movies>

    @GET(NOW_PLAYING)
    suspend fun getNowPlayingMovies() : ApiResponse<Movies>


}


private object MovieRequestPath {
     const val  POPULAR ="movie/popular"

      const val DETAILS  ="movie/{idMovie}"

     const val CREDIT  ="movie/{idMovie}/credits"

     const val SIMILAR = "movie/{idMovie}/similar"

     const val LATEST = "movie/latest"

     const val UP_COMING ="movie/upcoming"

     const val TOP_RATED ="movie/top_rated"

     const val NOW_PLAYING ="movie/now_playing"
}