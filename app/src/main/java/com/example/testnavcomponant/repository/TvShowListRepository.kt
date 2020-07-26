package com.example.testnavcomponant.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.testnavcomponant.NetworkState
import com.example.testnavcomponant.dataSource.TvShowDataSource
import com.example.testnavcomponant.dataSource.TvShowDataSourceFactory
import com.example.testnavcomponant.network.MovieService
import com.example.testnavcomponant.network.POST_PER_PAGE
import com.example.testnavcomponant.pojo.movie.MoviesApiResult
import com.example.testnavcomponant.pojo.tvShow.TvShowApiResult
import io.reactivex.disposables.CompositeDisposable

class TvShowListRepository (private val apiService: MovieService) {
    lateinit var tvShowPagedList : LiveData<PagedList<TvShowApiResult>>
    lateinit var tvShowDataSourceFactory: TvShowDataSourceFactory

    fun fetchTvShowPagedList(tvDisposable: CompositeDisposable): LiveData<PagedList<TvShowApiResult>>{
        tvShowDataSourceFactory = TvShowDataSourceFactory(apiService,tvDisposable)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()
        tvShowPagedList = LivePagedListBuilder(tvShowDataSourceFactory,config).build()

        return tvShowPagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<TvShowDataSource, NetworkState>(
            tvShowDataSourceFactory.tvShowLiveDataSource, TvShowDataSource::networkState
        )
    }
}