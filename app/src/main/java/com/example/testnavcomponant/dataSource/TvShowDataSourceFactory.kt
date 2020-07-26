package com.example.testnavcomponant.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.testnavcomponant.network.MovieService
import com.example.testnavcomponant.pojo.tvShow.TvShowApiResult
import io.reactivex.disposables.CompositeDisposable

class TvShowDataSourceFactory (private val apiService: MovieService, private val tvDisposable: CompositeDisposable) :
    DataSource.Factory<Int, TvShowApiResult>()   {
    val tvShowLiveDataSource = MutableLiveData<TvShowDataSource>()
    override fun create(): DataSource<Int, TvShowApiResult> {
        val tvShowDataSource = TvShowDataSource(apiService, tvDisposable)
        tvShowLiveDataSource.postValue(tvShowDataSource)
        return tvShowDataSource
    }
}