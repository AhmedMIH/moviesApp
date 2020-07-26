package com.example.testnavcomponant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testnavcomponant.R
import com.example.testnavcomponant.network.POSTER_BASE_URL
import com.example.testnavcomponant.pojo.movie.MoviesApiResult
import com.example.testnavcomponant.ui.MoviesListFragment
import kotlinx.android.synthetic.main.list_item.view.*

class PopularMoviesAdapter() :
    PagedListAdapter<MoviesApiResult, RecyclerView.ViewHolder>(
        MovieDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View
        view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return MovieItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieItemViewHolder).bind(getItem(position))

    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<MoviesApiResult>() {
    override fun areItemsTheSame(oldItem: MoviesApiResult, newItem: MoviesApiResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MoviesApiResult, newItem: MoviesApiResult): Boolean {
        return oldItem == newItem
    }

}

class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        movie: MoviesApiResult?
    ) {
        val movieName = itemView.NameTV
        movieName.text = movie?.title
        val moviePosterURL = POSTER_BASE_URL + movie?.posterPath
        Glide.with(itemView.context)
            .load(moviePosterURL)
            .into(itemView.posterIV)

        itemView.setOnClickListener {
            val bundle = bundleOf("MovieID" to movie!!.id)
            itemView.findNavController()
                .navigate(R.id.action_moviesListFragment_to_detailsFragment, bundle)
        }
    }
}