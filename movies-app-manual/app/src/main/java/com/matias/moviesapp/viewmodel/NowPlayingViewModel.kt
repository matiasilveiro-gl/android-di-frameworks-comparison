package com.matias.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.matias.domain.entity.Movie
import com.matias.domain.usecase.GetNowPlayingMoviesUseCase
import com.matias.domain.util.ResultOf
import com.matias.moviesapp.MainApplication
import com.matias.moviesapp.util.LiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NowPlayingViewModel(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {

    companion object {
        const val TAG = "NowPlayingViewModel"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val getNowPlayingMoviesUseCase = (this[APPLICATION_KEY] as MainApplication)
                    .appContainer
                    .getNowPlayingMoviesUseCase
                NowPlayingViewModel(getNowPlayingMoviesUseCase)
            }
        }
    }

    private val _moviesData: MutableLiveData<MoviesData> = MutableLiveData()
    val moviesData: LiveData<MoviesData> get() = _moviesData

    private val _navigation = LiveEvent<Navigation>()
    val navigation: LiveData<Navigation> get() = _navigation

    fun onMoviePressed(movieId: Int) {
        _navigation.postValue(Navigation.ToMovieDetails(movieId))
    }

    sealed class Navigation {
        data class ToMovieDetails(val movieId: Int) : Navigation()
    }

    fun getNowPlayingMovies() = viewModelScope.launch {
        _moviesData.postValue(MoviesData(state = MoviesData.State.LOADING))
        withContext(Dispatchers.IO) { getNowPlayingMoviesUseCase() }.let { result ->
            when (result) {
                is ResultOf.Success -> {
                    if (result.value.isNotEmpty()) {
                        _moviesData.postValue(
                            MoviesData(state = MoviesData.State.SUCCESS, data = result.value)
                        )
                    } else {
                        _moviesData.postValue(MoviesData(state = MoviesData.State.EMPTY))
                    }
                }

                is ResultOf.Failure -> {
                    _moviesData.postValue(MoviesData(state = MoviesData.State.FAILURE, error = result.throwable))
                }
            }
        }
    }

    data class MoviesData(
        val state: State,
        val data: List<Movie> = emptyList(),
        val error: Throwable? = null
    ) {
        enum class State {
            SUCCESS, EMPTY, FAILURE, LOADING
        }
    }
}
