package com.example.testnavcomponant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.testnavcomponant.pojo.movie.MovieDetails
import com.example.testnavcomponant.repository.MovieDetailsRepository
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsViewModel(movieDetailsRepository: MovieDetailsRepository, movieId:Int):ViewModel() {
    private val movieDisposable = CompositeDisposable()

    init {
        movieDetailsRepository.fetchMovieDetails(movieId,movieDisposable)
    }

     val movieDetails : LiveData<MovieDetails> by lazy {
         movieDetailsRepository.singleMovieResponse
    }

    override fun onCleared() {
        super.onCleared()
        movieDisposable.clear()
    }
}