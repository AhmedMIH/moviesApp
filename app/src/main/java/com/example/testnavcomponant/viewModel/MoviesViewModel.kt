package com.example.testnavcomponant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.testnavcomponant.NetworkState
import com.example.testnavcomponant.pojo.movie.MoviesApiResult
import com.example.testnavcomponant.repository.MoviesListRepository
import io.reactivex.disposables.CompositeDisposable

class MoviesViewModel(private val moviesRepository: MoviesListRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val networkState: LiveData<NetworkState> by lazy {
        moviesRepository.getNetworkState()
    }
    val moviePagedList: LiveData<PagedList<MoviesApiResult>> by lazy {
        moviesRepository.fetchLiveMoviePagedList(compositeDisposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}