package com.example.testnavcomponant.pojo.movie

import com.example.testnavcomponant.pojo.movie.Genres
import com.google.gson.annotations.SerializedName

data class MovieDetails (
    val id: Int,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val genres: List<Genres>,
    val runtime: Int,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("vote_count")
    val ratingCount:Int,
    val original_language:String
)