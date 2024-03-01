package com.asad.smallworldandroidtest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true) val page: Int = 0,
    @SerializedName("results") val results: ArrayList<Result> = arrayListOf(),
    @SerializedName("total_pages") val totalPages: Int = 0,
    @SerializedName("total_results") val totalResults: Int = 0,
)