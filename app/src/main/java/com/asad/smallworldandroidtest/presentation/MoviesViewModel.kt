package com.asad.smallworldandroidtest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.smallworldandroidtest.data.model.Movie
import com.asad.smallworldandroidtest.data.model.MovieDetail
import com.asad.smallworldandroidtest.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie
    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> get() = _movieDetail
    private val _filteredMove = MutableLiveData<Movie>()
    val filteredMove: LiveData<Movie> get() = _filteredMove

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
                _movieDetail.value = repository.getMovieDetail(movieId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun filterMovie(movieName: String) {
        viewModelScope.launch {
            try {
                _filteredMove.value = repository.getFilteredMovie(movieName)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}