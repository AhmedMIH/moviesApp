package com.example.testnavcomponant.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testnavcomponant.network.MovieService
import com.example.testnavcomponant.pojo.movie.MoviesApiResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieRecommendationRepository(
    private val apiService: MovieService
) {
    private val movieRecommendationResponse = MutableLiveData<MoviesApiResponse>()
    val singleRecommendationResponse: LiveData<MoviesApiResponse>
        get() = movieRecommendationResponse

    fun fetchMovieRecommendation(movieId:Int,movieDisposable: CompositeDisposable){
        movieDisposable.add(
            apiService.getRecommendations(movieId,1)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        movieRecommendationResponse.postValue(it)
                    },
                    {
                        Log.e("RecommendedDataSource", it.message!!)
                    }
                )
        )
    }
}