package com.asad.smallworldandroidtest

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET(ApiEndpoints.GET_MOVIES_LIST)
    suspend fun getMovieList(): Movie

    @GET(ApiEndpoints.GET_MOVIE_DETAIL)
    suspend fun getMovieDetail(@Path("movie_id") movieId: Long): MovieDetail
}