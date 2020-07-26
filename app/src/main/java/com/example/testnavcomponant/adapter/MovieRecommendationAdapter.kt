package com.example.testnavcomponant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testnavcomponant.network.POSTER_BASE_URL
import com.example.testnavcomponant.pojo.movie.MoviesApiResult
import com.example.testnavcomponant.R
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.recommendation_list_item.view.*

class MovieRecommendationAdapter(
    private val context: Context,
    private val movies: List<MoviesApiResult>
) : RecyclerView.Adapter<RecommendItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recommendation_list_item, parent, false)
        return RecommendItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecommendItemViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class RecommendItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(movie: MoviesApiResult?) {
        val movieName = itemView.recommended_movie_TV
        movieName.text = movie?.title
        val moviePosterURL = POSTER_BASE_URL + movie?.posterPath
        Glide.with(itemView.context)
            .load(moviePosterURL)
            .into(itemView.recommended_movie_IV)

        itemView.setOnClickListener {
            itemView.findNavController().navigate(R.id.action_recommendationBottomSheetFragment_to_detailsFragment,
                bundleOf("MovieID" to movie!!.id))
        }
    }

}
