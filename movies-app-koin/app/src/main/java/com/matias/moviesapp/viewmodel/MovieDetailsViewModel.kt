package com.matias.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matias.domain.entity.MovieDetails
import com.matias.domain.usecase.GetMovieByIdUseCase
import com.matias.domain.util.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(
    private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel() {

    companion object {
        const val TAG = "MovieDetailsViewModel"
    }

    private val _movieData: MutableLiveData<MovieData> = MutableLiveData()
    val movieData: LiveData<MovieData> get() = _movieData

    fun getMovie(id: Int) = viewModelScope.launch {
        _movieData.postValue(MovieData(state = MovieData.State.LOADING))
        withContext(Dispatchers.IO) { getMovieByIdUseCase(id) }.let { result ->
            when (result) {
                is ResultOf.Success -> {
                    _movieData.postValue(
                        MovieData(state = MovieData.State.SUCCESS, data = result.value)
                    )
                }
                is ResultOf.Failure -> {
                    _movieData.postValue(MovieData(state = MovieData.State.FAILURE, error = result.throwable))
                }
            }
        }
    }

    data class MovieData(
        val state: State,
        val data: MovieDetails? = null,
        val error: Throwable? = null
    ) {
        enum class State {
            SUCCESS, FAILURE, LOADING
        }
    }
}
