package com.druide.flexmovies.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.druide.flexmovies.common.Resource
import com.druide.flexmovies.domain.model.Movies
import com.druide.flexmovies.domain.model.Results
import com.druide.flexmovies.domain.movies.MoviesUseCase
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel  @Inject constructor( private val  moviesUseCase: MoviesUseCase) : ViewModel() {

    private var _moviesState: MutableLiveData<Resource<Movies>> = MutableLiveData()
    val moviesState: LiveData<Resource<Movies>> = _moviesState


    /**
     * Get movie according to the selected page
     *
     * @param pageIndex Int by default the value should be 1
     */
    fun getMovieAtPage(pageIndex: Int) {
        _moviesState.postValue(Resource.Loading())
        viewModelScope.launch {
            val response = moviesUseCase.getPopularMovies(pageIndex)

            response.onSuccess {
                if  ( this.statusCode.code == 200 && this.response.isSuccessful) {
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

}