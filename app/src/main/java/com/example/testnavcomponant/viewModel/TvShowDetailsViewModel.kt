package com.example.testnavcomponant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.testnavcomponant.pojo.tvShow.TvShowDetails
import com.example.testnavcomponant.repository.TvShowDetailsRepository
import io.reactivex.disposables.CompositeDisposable

class TvShowDetailsViewModel(repository: TvShowDetailsRepository,tvId:Int):ViewModel() {
    private val disposable = CompositeDisposable()

    init {
        repository.fetchTvShowDetails(tvId,disposable)
    }

    val tvShowDetails : LiveData<TvShowDetails> by lazy {
        repository.singleTvShowResponse
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}