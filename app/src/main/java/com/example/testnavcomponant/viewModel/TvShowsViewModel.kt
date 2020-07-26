package com.example.testnavcomponant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.testnavcomponant.NetworkState
import com.example.testnavcomponant.pojo.movie.MoviesApiResult
import com.example.testnavcomponant.pojo.tvShow.TvShowApiResult
import com.example.testnavcomponant.repository.TvShowListRepository
import io.reactivex.disposables.CompositeDisposable

class TvShowsViewModel(private val tvShowListRepository: TvShowListRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val networkState : LiveData<NetworkState> by lazy {
        tvShowListRepository.getNetworkState()
    }
    val tvShowPagedList : LiveData<PagedList<TvShowApiResult>> by lazy {
        tvShowListRepository.fetchTvShowPagedList(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}