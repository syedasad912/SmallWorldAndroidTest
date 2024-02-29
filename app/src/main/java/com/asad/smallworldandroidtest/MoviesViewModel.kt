package com.asad.smallworldandroidtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> get() = _movieDetail

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                _movie.value = repository.getMoviesList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchMovieDetail(movieId: Long) {
        viewModelScope.launch {
            try {
                _movieDetail.value = repository.getMoviesDetail(movieId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}