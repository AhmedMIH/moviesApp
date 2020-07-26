package com.example.testnavcomponant.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testnavcomponant.network.MovieService
import com.example.testnavcomponant.pojo.movie.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsRepository(
    private val apiService: MovieService
) {

    private val movieDetailsResponse = MutableLiveData<MovieDetails>()
    val singleMovieResponse: LiveData<MovieDetails>
        get() = movieDetailsResponse

    fun fetchMovieDetails(movieId:Int,movieDisposable: CompositeDisposable){
        movieDisposable.add(
            apiService.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        movieDetailsResponse.postValue(it)

                    },
                    {
                        Log.e("MovieDetailsDataSource", it.message!!)
                    }
                )
        )
    }

}