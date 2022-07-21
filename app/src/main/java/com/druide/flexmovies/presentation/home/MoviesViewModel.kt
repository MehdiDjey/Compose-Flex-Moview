package com.druide.flexmovies.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private var _movies: MutableLiveData<Movies?> = MutableLiveData()
    val movies: LiveData<Movies?> = _movies

    private var _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private var _canLoadMore: MutableLiveData<Boolean> = MutableLiveData()
    val canLoadMore: LiveData<Boolean> = _canLoadMore

    private var currentPage = -1

    var fetchedList = listOf<Results>()


    /**
     * Get movie according to the selected page
     *
     * @param pageIndex Int by default the value should be 1
     */
    fun getMovieAtPage(pageIndex: Int) {
        currentPage = pageIndex
        viewModelScope.launch {
            val response = moviesUseCase.getPopularMovies(pageIndex)

            response.onSuccess {
                if  ( this.statusCode.code == 200 && this.response.isSuccessful) {
                    //fetchedList.addAll(data.results)
                    _movies.value = data
                    _canLoadMore.value = currentPage < data.totalPages
                }
            }
            response.onError {
                _error.value =
                    "${this.message()} [ Code : ${this.statusCode.code}], check your internet connection and retry"
            }

            response.onException {
                _error.value = "Something wrong with : ${this.message()}"
            }
        }
    }

}