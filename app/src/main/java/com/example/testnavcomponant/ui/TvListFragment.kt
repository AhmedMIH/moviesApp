package com.example.testnavcomponant.ui

import android.os.Bundle
import android.util.Log
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
import com.example.testnavcomponant.adapter.PopularTVShowAdapter
import com.example.testnavcomponant.network.MovieApiClient
import com.example.testnavcomponant.repository.TvShowListRepository
import com.example.testnavcomponant.viewModel.TvShowsViewModel
import kotlinx.android.synthetic.main.fragment_tv_list.*

class TvListFragment : Fragment() {
    private lateinit var tvViewModel: TvShowsViewModel
    private lateinit var tvRepository: TvShowListRepository
    private val apiService = MovieApiClient.movieService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        tvRepository = TvShowListRepository(apiService)
        tvViewModel = getViewModel()
        val tvAdapter = PopularTVShowAdapter(activity!!.applicationContext)
        tvViewModel.tvShowPagedList.observe(viewLifecycleOwner, Observer {
            tvAdapter.submitList(it)
            Log.d("test",it.toString())
            rv_tv_list.adapter = tvAdapter
        })
        rv_tv_list.layoutManager = staggeredGridLayoutManager
    }

    private fun getViewModel(): TvShowsViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TvShowsViewModel(tvRepository) as T
            }
        })[TvShowsViewModel::class.java]
    }
}