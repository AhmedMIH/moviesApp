package com.example.testnavcomponant.pojo.tvShow

import com.example.testnavcomponant.pojo.movie.Genres
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TvShowDetails(

    @SerializedName("episode_run_time")
    @Expose
    var episodeRunTime: List<Int>? = null,

    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String? = null,

    @SerializedName("genres")
    @Expose
    var genres: List<Genres>? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("languages")
    @Expose
    var languages: List<String>? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,


    @SerializedName("number_of_episodes")
    @Expose
    var numberOfEpisodes: Int? = null,

    @SerializedName("number_of_seasons")
    @Expose
    var numberOfSeasons: Int? = null,

    @SerializedName("overview")
    @Expose
    var overview: String? = null,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = null,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
)