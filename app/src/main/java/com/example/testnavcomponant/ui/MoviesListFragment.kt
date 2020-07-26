package com.example.testnavcomponant.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.testnavcomponant.R
import com.example.testnavcomponant.adapter.PopularMoviesAdapter
import com.example.testnavcomponant.network.MovieApiClient
import com.example.testnavcomponant.repository.MoviesListRepository
import com.example.testnavcomponant.viewModel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies_list.*

class MoviesListFragment : Fragment() {
    private lateinit var moviesViewModel: MoviesViewModel
    lateinit var movieRepository: MoviesListRepository
    private val apiService = MovieApiClient.movieService()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieRepository = MoviesListRepository(apiService)
        moviesViewModel = getViewModel()

        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress_bar.visibility = View.VISIBLE

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        val moviesAdapter = PopularMoviesAdapter()
        moviesViewModel.moviePagedList.observe(viewLifecycleOwner, Observer {
            moviesAdapter.submitList(it)
            rv_movie_list.adapter = moviesAdapter
            progress_bar.visibility = View.GONE
        })
        rv_movie_list.layoutManager = staggeredGridLayoutManager




    }

    private fun getViewModel(): MoviesViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MoviesViewModel(movieRepository) as T
            }
        })[MoviesViewModel::class.java]
    }

}