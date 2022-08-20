package com.druide.flexmovies.presentation.home.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.druide.flexmovies.common.Resource
import com.druide.flexmovies.common.utils.awaitAll
import com.druide.flexmovies.domain.tvShow.TvShowUseCase
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
class TvShowViewModel @Inject constructor( private val tvShowUseCase: TvShowUseCase) : ViewModel() {

    private val _popularState = MutableStateFlow<Resource>(Resource.Empty)
    val popularState :StateFlow<Resource> = _popularState


    private val _topRatedState = MutableStateFlow<Resource>(Resource.Empty)
    val topRated :StateFlow<Resource>  = _topRatedState


    private val _latestState  = MutableStateFlow<Resource>(Resource.Empty)
    val latestState : StateFlow<Resource> = _latestState

    private val _onTheAirState  = MutableStateFlow<Resource>(Resource.Empty)
    val onTheAirState : StateFlow<Resource> = _onTheAirState

    private val _todayAiringState  = MutableStateFlow<Resource>(Resource.Empty)
    val todayAiringState : StateFlow<Resource> = _todayAiringState




    suspend fun getAllTvShowContent() = coroutineScope {
        awaitAll(
            ::getPopularTvShow,
            ::getTopRatedTvShow,
            ::getOnTheAirTvShow,
            ::getTodayAiringTvShow,
            ::getLatestTvShow
        )
    }


    private fun getPopularTvShow() {
        _popularState.value = Resource.Loading

        viewModelScope.launch {
            val response = tvShowUseCase.getPopular()

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

    private fun getTopRatedTvShow () {
        _topRatedState.value = Resource.Loading
        viewModelScope.launch {
            val response = tvShowUseCase.getTopRated()

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

    private fun getOnTheAirTvShow() {
        _onTheAirState.value = Resource.Loading
        viewModelScope.launch {
            val response = tvShowUseCase.getOnTheAir()

            response.onSuccess {
                if (this.statusCode.code == 200 && this.response.isSuccessful) {
                    _onTheAirState.value = Resource.Success(data)
                }
            }
            response.onError {
                _onTheAirState.value = Resource.Error(this.message())
            }

            response.onException {
                _onTheAirState.value = Resource.Error(this.message())
            }
        }


    }

    private fun getTodayAiringTvShow() {
        _todayAiringState.value = Resource.Loading
        viewModelScope.launch {
            val response = tvShowUseCase.getTodayAiring()

            response.onSuccess {
                if (this.statusCode.code == 200 && this.response.isSuccessful) {
                    _todayAiringState.value = Resource.Success(data)
                }
            }
            response.onError {
                _todayAiringState.value = Resource.Error(this.message())
            }

            response.onException {
                _todayAiringState.value = Resource.Error(this.message())
            }
        }

    }

    private fun getLatestTvShow() {
        _latestState.value = Resource.Loading
        viewModelScope.launch {
            val response = tvShowUseCase.getLatest()

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

}