package com.example.testnavcomponant.dataSource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.testnavcomponant.NetworkState
import com.example.testnavcomponant.network.FIRST_PAGE
import com.example.testnavcomponant.network.MovieService
import com.example.testnavcomponant.pojo.tvShow.TvShowApiResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TvShowDataSource(
    private val apiService: MovieService,
    private val tvDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, TvShowApiResult>() {
    private var page = FIRST_PAGE
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShowApiResult>
    ) {
        tvDisposable.add(
            apiService.getPopularTv(page)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    callback.onResult(it.itemsList, null, page + 1)
                    Log.d("test",it.toString())
                    networkState.postValue(NetworkState.LOADED)
                }, {
                    networkState.postValue(NetworkState.ERROR)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShowApiResult>) {
        tvDisposable.add(
            apiService.getPopularTv(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (it.totalPages >= params.key) {
                        callback.onResult(it.itemsList, params.key + 1)
                        networkState.postValue(NetworkState.LOADED)
                    } else
                        networkState.postValue(NetworkState.ENDOFLIST)
                }, {
                    networkState.postValue(NetworkState.ERROR)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShowApiResult>) {
    }
}