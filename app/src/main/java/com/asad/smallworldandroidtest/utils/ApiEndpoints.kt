package com.asad.smallworldandroidtest.utils

object ApiEndpoints {
    const val GET_MOVIES_LIST = "discover/movie?sort_by=popularity.desc"
    const val GET_MOVIE_DETAIL = "movie/{movie_id}?language=en-US"
    const val FILTER_MOVIE = "search/movie"
}