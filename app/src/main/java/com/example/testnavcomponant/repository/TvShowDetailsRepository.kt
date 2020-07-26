package com.example.testnavcomponant.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testnavcomponant.network.MovieService
import com.example.testnavcomponant.pojo.tvShow.TvShowDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TvShowDetailsRepository (private val apiService: MovieService
) {

    private val tvShowDetailsResponse = MutableLiveData<TvShowDetails>()
    val singleTvShowResponse: LiveData<TvShowDetails>
        get() = tvShowDetailsResponse

    fun fetchTvShowDetails(tvId: Int, disposable: CompositeDisposable) {
        disposable.add(
            apiService.getTvShowDetails(tvId)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        tvShowDetailsResponse.postValue(it)

                    },
                    {
                        Log.e("MovieDetailsDataSource", it.message!!)
                    }
                )
        )
    }
}
