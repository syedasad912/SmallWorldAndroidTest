package com.asad.smallworldandroidtest

class MovieRepository(private val apiService: ApiService) {

    suspend fun getMoviesList(): Movie {
        return apiService.apiService.getMovieList()
    }

    suspend fun getMoviesDetail(movieId: Long): MovieDetail {
        return apiService.apiService.getMovieDetail(movieId)
    }
}