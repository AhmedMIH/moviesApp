package com.example.testnavcomponant.pojo.movie

import com.google.gson.annotations.SerializedName

data class MoviesApiResult(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    val title: String

)