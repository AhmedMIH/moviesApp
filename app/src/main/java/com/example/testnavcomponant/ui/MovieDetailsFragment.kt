package com.example.testnavcomponant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.testnavcomponant.R
import com.example.testnavcomponant.network.MovieApiClient
import com.example.testnavcomponant.network.POSTER_BASE_URL
import com.example.testnavcomponant.pojo.movie.MovieDetails
import com.example.testnavcomponant.repository.MovieDetailsRepository
import com.example.testnavcomponant.viewModel.MovieDetailsViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment() {
    lateinit var moviesDetailsViewModel: MovieDetailsViewModel
    lateinit var movieDetailsRepository: MovieDetailsRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiService = MovieApiClient.movieService()
        val movieId = arguments?.get("MovieID")
        movieDetailsRepository = MovieDetailsRepository(apiService)
        moviesDetailsViewModel = getViewModel(movieId as Int)
        moviesDetailsViewModel.movieDetails.observe(this, Observer {
            displayDataInUi(it)
        })
        val bundle = bundleOf("ID" to movieId)
        recommended_Button.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_recommendationBottomSheetFragment,bundle)
        }
    }
    private fun displayDataInUi(movie: MovieDetails) {
        movieName.text = movie.title
        Glide.with(this)
            .load(POSTER_BASE_URL + movie.posterPath)
            .into(movieImage)
        movieYear.text = movie.releaseDate
        runtime.text = "${movie.runtime}m"
        genres.text = movie.genres.joinToString() { it.name.toString() }
        lang.text = movie.original_language
        rating.text = "${movie.rating}/10"
        ratingNumber.text = movie.ratingCount.toString()
        overView.text = movie.overview
    }


    private fun getViewModel(movieId: Int): MovieDetailsViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailsViewModel(movieDetailsRepository, movieId) as T
            }
        })[MovieDetailsViewModel::class.java]
    }
    
}