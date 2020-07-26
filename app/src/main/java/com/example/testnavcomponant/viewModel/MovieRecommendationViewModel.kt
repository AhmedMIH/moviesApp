package com.example.testnavcomponant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.testnavcomponant.pojo.movie.MoviesApiResponse
import com.example.testnavcomponant.repository.MovieRecommendationRepository
import io.reactivex.disposables.CompositeDisposable

class MovieRecommendationViewModel (movieRepository: MovieRecommendationRepository, movieId:Int):ViewModel() {
    private val movieDisposable = CompositeDisposable()

    init {
        movieRepository.fetchMovieRecommendation(movieId, movieDisposable)
    }

    val moviesList : LiveData<MoviesApiResponse> by lazy {
        movieRepository.singleRecommendationResponse
    }
    override fun onCleared() {
        super.onCleared()
        movieDisposable.clear()
    }
}