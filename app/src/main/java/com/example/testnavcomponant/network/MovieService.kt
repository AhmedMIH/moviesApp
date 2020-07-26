package com.example.testnavcomponant.network

import com.example.testnavcomponant.pojo.tvShow.TvShowApiResponce
import com.example.testnavcomponant.pojo.movie.MovieDetails
import com.example.testnavcomponant.pojo.movie.MoviesApiResponse
import com.example.testnavcomponant.pojo.tvShow.TvShowDetails
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Observable<MoviesApiResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Observable<MovieDetails>

    @GET("movie/{movie_id}/recommendations")
    fun getRecommendations(
        @Path("movie_id") id: Int,
        @Query("page") page: Int
    ): Observable<MoviesApiResponse>

    @GET("tv/popular")
    fun getPopularTv(@Query("page") page: Int): Observable<TvShowApiResponce>

    @GET("tv/{tv_id}")
    fun getTvShowDetails(@Path("tv_id") id: Int): Observable<TvShowDetails>
}