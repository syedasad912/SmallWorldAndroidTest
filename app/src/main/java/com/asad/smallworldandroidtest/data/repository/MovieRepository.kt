package com.asad.smallworldandroidtest.data.repository

import com.asad.smallworldandroidtest.data.model.Movie
import com.asad.smallworldandroidtest.data.model.MovieDetail
import com.asad.smallworldandroidtest.utils.ApiService

class MovieRepository(private val apiService: ApiService) {

    suspend fun getMoviesList(): Movie {
        return apiService.apiService.getMovieList()
    }

    suspend fun getMovieDetail(movieId: Long): MovieDetail {
        return apiService.apiService.getMovieDetail(movieId)
    }

    suspend fun getFilteredMovie(movieName: String): Movie {
        return apiService.apiService.getFilteredMovie(movieName)
    }
}