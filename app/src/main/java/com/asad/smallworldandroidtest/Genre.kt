package com.asad.smallworldandroidtest

import com.google.gson.annotations.SerializedName


class Genre {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null
}