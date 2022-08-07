package com.druide.flexmovies.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.druide.flexmovies.common.Resource
import com.druide.flexmovies.domain.model.Movies
import com.druide.flexmovies.domain.movies.MoviesUseCase
import com.druide.flexmovies.domain.tvShow.TvShowUseCase
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase, private val tvShowUseCase: TvShowUseCase) :ViewModel() {

    private val _movieDetailState = MutableStateFlow<Resource>(Resource.Empty)
    val movieDetailState: StateFlow<Resource> = _movieDetailState


    private val _movieSimilarState = MutableStateFlow<Resource>(Resource.Empty)
    val movieSimilarState: StateFlow<Resource> = _movieSimilarState

    private val _movieCastState = MutableStateFlow<Resource>(Resource.Empty)
    val movieCastState: StateFlow<Resource> = _movieCastState


    private val _tvShowDetailState = MutableStateFlow<Resource>(Resource.Empty)
    val tvShowDetailState: StateFlow<Resource> = _tvShowDetailState


    private var movieId = -1

    fun getMovieDetail(idMovie : Int) {
        movieId = idMovie
        _movieDetailState.value = Resource.Loading

        viewModelScope.launch {
            val response = moviesUseCase.getDetails(idMovie)

            response.onSuccess {
                if (this.statusCode.code == 200 && this.response.isSuccessful) {
                    _movieDetailState.value = Resource.Success(data)
                }
            }

            response.onError {
                _movieDetailState.value = Resource.Error(this.message())
            }

            response.onException {
                _movieDetailState.value = Resource.Error(this.message())
            }
        }
    }

    fun getMovieCast() {
        _movieCastState.value = Resource.Loading

        viewModelScope.launch {
            val response = moviesUseCase.getCredit(movieId)
            response.onSuccess {
                _movieCastState.value = Resource.Success(data)
            }

            response.onError {
                _movieCastState.value =  Resource.Error(this.message())
            }

            response.onException {
                _movieCastState.value =   Resource.Error(this.message())
            }
        }

    }

    fun getSimilarMovies() {
        _movieSimilarState.value = Resource.Loading

        viewModelScope.launch {
            val response = moviesUseCase.getSimilar(movieId)

            response.onSuccess {
                _movieSimilarState.value = Resource.Success(data)

            }

            response.onError {
                _movieSimilarState.value  = Resource.Error(this.message())

            }

            response.onException {
                _movieSimilarState.value  = Resource.Error(this.message())
            }
        }

    }

}