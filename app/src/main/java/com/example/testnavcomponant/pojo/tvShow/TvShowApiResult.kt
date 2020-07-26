package com.example.testnavcomponant.pojo.tvShow

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class TvShowApiResult (
    @SerializedName("original_name")
    var originalName: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("id")
    var id: Int,
    @SerializedName("poster_path")
    var posterPath: String?
)