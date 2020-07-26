package com.example.testnavcomponant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.testnavcomponant.R
import com.example.testnavcomponant.network.MovieApiClient
import com.example.testnavcomponant.network.POSTER_BASE_URL
import com.example.testnavcomponant.pojo.tvShow.TvShowDetails
import com.example.testnavcomponant.repository.TvShowDetailsRepository
import com.example.testnavcomponant.viewModel.TvShowDetailsViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_movie_details.genres
import kotlinx.android.synthetic.main.fragment_movie_details.lang
import kotlinx.android.synthetic.main.fragment_movie_details.overView
import kotlinx.android.synthetic.main.fragment_movie_details.rating
import kotlinx.android.synthetic.main.fragment_movie_details.ratingNumber
import kotlinx.android.synthetic.main.fragment_movie_details.runtime
import kotlinx.android.synthetic.main.fragment_tv_show_details.*

class TvShowDetailsFragment : Fragment() {

    lateinit var tvShowDetailsViewModel: TvShowDetailsViewModel
    lateinit var tvShowDetailsRepository: TvShowDetailsRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiService = MovieApiClient.movieService()
        tvShowDetailsRepository = TvShowDetailsRepository(apiService)
        val tvId = arguments?.get("tvId")
        tvShowDetailsViewModel = getViewModel(tvId as Int)
        tvShowDetailsViewModel.tvShowDetails.observe(this, Observer {
            displayDataInUi(it)

        })
    }

    private fun displayDataInUi(tvShow: TvShowDetails) {
        tvShowName.text = tvShow.name
        Glide.with(this)
            .load(POSTER_BASE_URL + tvShow.posterPath)
            .into(tvShowImage)
        tvShowYear.text = tvShow.firstAirDate
        runtime.text = "${tvShow.episodeRunTime}m"
        genres.text = tvShow.genres!!.joinToString() { it.name.toString() }
        lang.text = tvShow.languages!![0]
        rating.text = "${tvShow.voteAverage}/10"
        ratingNumber.text = tvShow.voteCount.toString()
        overView.text = tvShow.overview
        numberOfEp.text = "${tvShow.numberOfEpisodes}Ep"
        numberOfSeasons.text = "${tvShow.numberOfSeasons}Seasons"
    }


    private fun getViewModel(Id: Int): TvShowDetailsViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TvShowDetailsViewModel(tvShowDetailsRepository, Id) as T
            }
        })[TvShowDetailsViewModel::class.java]
    }

}