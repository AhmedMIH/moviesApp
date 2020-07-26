package com.example.testnavcomponant.dataSource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.testnavcomponant.NetworkState
import com.example.testnavcomponant.network.FIRST_PAGE
import com.example.testnavcomponant.network.MovieService
import com.example.testnavcomponant.pojo.movie.MoviesApiResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesDataSource(
    private val apiService: MovieService,
    private val movieDisposable: CompositeDisposable
) :
    PageKeyedDataSource<Int, MoviesApiResult>() {
    private var page = FIRST_PAGE
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MoviesApiResult>
    ) {
        movieDisposable.add(
            apiService.getPopularMovie(page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.itemsList, null, page + 1)
                        networkState.postValue(NetworkState.LOADED)
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MoviesApiResult>) {
        movieDisposable.add(
            apiService.getPopularMovie(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if (it.totalPages >= params.key) {
                            callback.onResult(it.itemsList, params.key + 1)
                            networkState.postValue(NetworkState.LOADED)
                        } else
                            networkState.postValue(NetworkState.ENDOFLIST)
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MoviesApiResult>) {
    }

}