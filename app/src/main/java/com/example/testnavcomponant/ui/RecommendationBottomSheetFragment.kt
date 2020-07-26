package com.example.testnavcomponant.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testnavcomponant.R
import com.example.testnavcomponant.adapter.MovieRecommendationAdapter
import com.example.testnavcomponant.network.MovieApiClient
import com.example.testnavcomponant.repository.MovieDetailsRepository
import com.example.testnavcomponant.repository.MovieRecommendationRepository
import com.example.testnavcomponant.viewModel.MovieDetailsViewModel
import com.example.testnavcomponant.viewModel.MovieRecommendationViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_recommendation_bottom_sheet.*

class RecommendationBottomSheetFragment : BottomSheetDialogFragment() {
    lateinit var moviesRecommendationViewModel: MovieRecommendationViewModel
    lateinit var movieRecommendationRepository: MovieRecommendationRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommendation_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = arguments?.get("ID")
        val apiService = MovieApiClient.movieService()
        movieRecommendationRepository = MovieRecommendationRepository(apiService)
        moviesRecommendationViewModel = getViewModel(movieId as Int)
        moviesRecommendationViewModel.moviesList.observe(this, Observer {
            val moviesAdapter = MovieRecommendationAdapter(activity!!.applicationContext,it.itemsList)
            recommended_RV.adapter = moviesAdapter
            recommended_RV.layoutManager = LinearLayoutManager(activity!!.applicationContext,LinearLayoutManager.VERTICAL,false)
        })

    }
    private fun getViewModel(movieId: Int): MovieRecommendationViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieRecommendationViewModel(movieRecommendationRepository, movieId) as T
            }
        })[MovieRecommendationViewModel::class.java]
    }
}