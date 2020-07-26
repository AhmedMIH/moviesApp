package com.example.testnavcomponant.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.testnavcomponant.network.MovieService
import com.example.testnavcomponant.pojo.movie.MoviesApiResult
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory(private val apiService: MovieService, private val movieDisposable: CompositeDisposable) :
DataSource.Factory<Int, MoviesApiResult>()  {
     val moviesLiveDataSource =  MutableLiveData<MoviesDataSource>()
    override fun create(): DataSource<Int, MoviesApiResult> {
        val moviesDataSource = MoviesDataSource(apiService,movieDisposable)
        moviesLiveDataSource.postValue(moviesDataSource)
        return moviesDataSource
    }
}