package com.example.testnavcomponant.pojo.tvShow

import com.google.gson.annotations.SerializedName

data class TvShowApiResponce(
    val page: Int,
    @SerializedName("results")
    val itemsList: List<TvShowApiResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)