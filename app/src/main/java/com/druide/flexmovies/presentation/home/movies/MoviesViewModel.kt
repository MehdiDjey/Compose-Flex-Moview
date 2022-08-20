package com.druide.flexmovies.presentation.home.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.druide.flexmovies.common.Resource
import com.druide.flexmovies.common.utils.awaitAll
import com.druide.flexmovies.domain.movies.MoviesUseCase
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) : ViewModel() {

    private val _popularState = MutableStateFlow<Resource>(Resource.Empty)
    val popularState: StateFlow<Resource> = _popularState


    private val _nowPlayingState = MutableStateFlow<Resource>(Resource.Empty)
    val nowPlayingState: StateFlow<Resource> = _nowPlayingState


    private val _topRatedState = MutableStateFlow<Resource>(Resource.Empty)
    val topRatedState: StateFlow<Resource> = _topRatedState


    private val _upComingState = MutableStateFlow<Resource>(Resource.Empty)
    val upComingState: StateFlow<Resource> = _upComingState


    private val _latestState = MutableStateFlow<Resource>(Resource.Empty)
    val latestState: StateFlow<Resource> = _latestState


    suspend fun getAllMoviesContent() = coroutineScope {
        awaitAll(
            ::getPopularMovies,
            ::getTopRatedMovies,
            ::getNowPlayingMovies,
            ::getUpComingMovies,
            ::getLatestMovies
        )
    }


    private fun getPopularMovies() {
        _popularState.value = Resource.Loading

        viewModelScope.launch {
            val response = moviesUseCase.getPopulars()

            response.onSuccess {
                if (this.statusCode.code == 200 && this.response.isSuccessful) {
                    _popularState.value = Resource.Success(data)
                }
            }
            response.onError {
                _popularState.value = Resource.Error(this.message())
            }

            response.onException {
                _popularState.value = Resource.Error(this.message())
            }
        }
    }

    private fun getTopRatedMovies() {
        _topRatedState.value = Resource.Loading
        viewModelScope.launch {
            val response = moviesUseCase.getTopRated()

            response.onSuccess {
                if (this.statusCode.code == 200 && this.response.isSuccessful) {
                    _topRatedState.value = Resource.Success(data)
                }
            }
            response.onError {
                _topRatedState.value = Resource.Error(this.message())
            }

            response.onException {
                _topRatedState.value = Resource.Error(this.message())
            }
        }
    }

    private fun getNowPlayingMovies() {
        _nowPlayingState.value = Resource.Loading

        viewModelScope.launch {
            val response = moviesUseCase.getNowPlaying()

            response.onSuccess {
                if (this.statusCode.code == 200 && this.response.isSuccessful) {
                    _nowPlayingState.value = Resource.Success(data)
                }
            }
            response.onError {
                _nowPlayingState.value = Resource.Error(this.message())
            }

            response.onException {
                _nowPlayingState.value = Resource.Error(this.message())
            }
        }
    }

    private fun getUpComingMovies() {
        _upComingState.value = Resource.Loading

        viewModelScope.launch {
            val response = moviesUseCase.getUpcoming()

            response.onSuccess {
                if (this.statusCode.code == 200 && this.response.isSuccessful) {
                    _upComingState.value = Resource.Success(data)
                }
            }
            response.onError {
                _upComingState.value = Resource.Error(this.message())
            }

            response.onException {
                _upComingState.value = Resource.Error(this.message())
            }
        }
    }

    private fun getLatestMovies() {
        _latestState.value = Resource.Loading

        viewModelScope.launch {
            val response = moviesUseCase.getLatest()

            response.onSuccess {
                if (this.statusCode.code == 200 && this.response.isSuccessful) {
                    _latestState.value = Resource.Success(data)
                }
            }
            response.onError {
                _latestState.value = Resource.Error(this.message())
            }

            response.onException {
                _latestState.value = Resource.Error(this.message())
            }
        }
    }

/*    fun getPopularTvShow() {
        _tvShowState.value = Resource.Loading

        viewModelScope.launch {
            val response = tvShowUseCase.getPopular()

            response.onSuccess {
                if (this.statusCode.code == 200 && this.response.isSuccessful) {
                    _tvShowState.value = Resource.Success(data)
                }
            }
            response.onError {
                _tvShowState.value = Resource.Error(this.message())
            }

            response.onException {
                _tvShowState.value = Resource.Error(this.message())
            }
        }
    }*/

}