package com.druide.flexmovies.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.druide.flexmovies.TAG
import com.druide.flexmovies.common.Resource
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
class MoviesViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase, private val tvShowUseCase: TvShowUseCase) : ViewModel() {

    private val _moviesState = MutableStateFlow<Resource>(Resource.Empty)
    val moviesState: StateFlow<Resource> = _moviesState


    private val _tvShowState = MutableStateFlow<Resource>(Resource.Empty)
    val tvShowState: StateFlow<Resource> = _tvShowState


    fun getPopularMovies() {
        _moviesState.value = Resource.Loading

        viewModelScope.launch {
            val response = moviesUseCase.getMoviesByPopularity()

            response.onSuccess {
                if (this.statusCode.code == 200 && this.response.isSuccessful) {
                    _moviesState.value = Resource.Success(data)
                }
            }
            response.onError {
                _moviesState.value = Resource.Error(this.message())
            }

            response.onException {
                _moviesState.value = Resource.Error(this.message())
            }
        }
    }

    fun getPopularTvShow() {
        _tvShowState.value = Resource.Loading

        viewModelScope.launch {
            val response = tvShowUseCase.getTvShowByPopularity()

            Log.d(TAG, "getPopularTvShow() called"+response)
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
    }

}