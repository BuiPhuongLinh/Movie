package com.btplinh.movies.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.btplinh.domain.basenetwork.NetworkStatus
import com.btplinh.domain.entities.Movie
import com.btplinh.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _listMovieLiveData =
        MutableLiveData<List<Movie>>(mutableListOf())
    val listMovieLiveData: LiveData<List<Movie>> = _listMovieLiveData

    private val _dataErrorLiveData =
        MutableLiveData<String>()
    val dataErrorLiveData: LiveData<String> = _dataErrorLiveData

    private val _showLoadingLiveData =
        MutableLiveData<Boolean>()
    val showLoadingLiveData: LiveData<Boolean> = _showLoadingLiveData

    fun getListMovie(page: Int, film: String = "disney") {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            _dataErrorLiveData.postValue(throwable.message)
        }) {
            movieUseCase.execute(film, page).collect {
                if (it is NetworkStatus.Success) {
                    _showLoadingLiveData.postValue(false)
                    if (!it.data.isNullOrEmpty()) {
                        _listMovieLiveData.postValue(it.data!!)
                    } else {
                        _dataErrorLiveData.postValue("List Empty!")
                    }
                } else if (it is NetworkStatus.Error) {
                    _showLoadingLiveData.postValue(false)
                    _dataErrorLiveData.postValue(if (it.errorMessage.isNullOrEmpty()) "Something wrong!" else it.errorMessage)
                } else {
                    _showLoadingLiveData.postValue(true)
                }
            }
        }
    }
}