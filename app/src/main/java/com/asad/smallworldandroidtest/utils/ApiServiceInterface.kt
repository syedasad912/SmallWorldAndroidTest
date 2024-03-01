package com.asad.smallworldandroidtest.utils

import com.asad.smallworldandroidtest.data.model.Movie
import com.asad.smallworldandroidtest.data.model.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET(ApiEndpoints.GET_MOVIES_LIST)
    suspend fun getMovieList(): Movie

    @GET(ApiEndpoints.GET_MOVIE_DETAIL)
    suspend fun getMovieDetail(@Path("movie_id") movieId: Long): MovieDetail
    @GET(ApiEndpoints.FILTER_MOVIE)
    suspend fun getFilteredMovie(@Query("query") movieName: String): Movie
}