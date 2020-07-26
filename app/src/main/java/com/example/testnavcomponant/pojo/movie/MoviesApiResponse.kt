package com.example.testnavcomponant.pojo.movie

import com.google.gson.annotations.SerializedName

data class MoviesApiResponse(
    val page: Int,
    @SerializedName("results")
    val itemsList: List<MoviesApiResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)