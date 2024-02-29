package com.asad.smallworldandroidtest

import com.google.gson.annotations.SerializedName

data class Movie(
    val page: Int,
    val results: ArrayList<Result>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
)