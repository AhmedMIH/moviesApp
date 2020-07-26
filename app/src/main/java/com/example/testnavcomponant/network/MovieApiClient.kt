package com.example.testnavcomponant.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20
const val API_KEY = "a8dd7a0f7b14b4a4ddcbc99b7a7e7aa3"
const val baseUrl = "https://api.themoviedb.org/3/"
const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

object MovieApiClient {
    private val movieService : MovieService

    init {
        val loggingInterceptor = Interceptor{
            val url = it.request()
                .url
                .newBuilder().addQueryParameter("api_key", API_KEY)
                .build()

            val request = it.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor it.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        movieService = retrofit.create(MovieService::class.java)

    }

    fun movieService(): MovieService = movieService
}