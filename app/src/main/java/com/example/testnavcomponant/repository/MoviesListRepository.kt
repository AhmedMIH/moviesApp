package com.example.testnavcomponant.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.testnavcomponant.NetworkState
import com.example.testnavcomponant.network.MovieService
import com.example.testnavcomponant.network.POST_PER_PAGE
import com.example.testnavcomponant.dataSource.MovieDataSourceFactory
import com.example.testnavcomponant.dataSource.MoviesDataSource
import com.example.testnavcomponant.pojo.movie.MoviesApiResult
import io.reactivex.disposables.CompositeDisposable

class MoviesListRepository(private val movieService: MovieService) {
    lateinit var moviePagedList: LiveData<PagedList<MoviesApiResult>>
    lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList(compositeDisposable: CompositeDisposable): LiveData<PagedList<MoviesApiResult>> {
        moviesDataSourceFactory = MovieDataSourceFactory(movieService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MoviesDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, MoviesDataSource::networkState
        )
    }
}