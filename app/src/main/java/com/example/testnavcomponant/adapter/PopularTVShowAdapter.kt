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
import com.example.testnavcomponant.pojo.tvShow.TvShowApiResult
import kotlinx.android.synthetic.main.list_item.view.*

class PopularTVShowAdapter(private val context: Context) :
    PagedListAdapter<TvShowApiResult, TvShowItemViewHolder>(TvDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return TvShowItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowItemViewHolder, position: Int) {
        holder.bind(getItem(position)!!, context)
    }

}

class TvDiffCallback : DiffUtil.ItemCallback<TvShowApiResult>() {
    override fun areItemsTheSame(oldItem: TvShowApiResult, newItem: TvShowApiResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TvShowApiResult, newItem: TvShowApiResult): Boolean {
        return oldItem == newItem
    }

}

class TvShowItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(tvShow: TvShowApiResult, context: Context) {


        val name = itemView.NameTV
        name.text = tvShow.name
        val posterURL = POSTER_BASE_URL + tvShow.posterPath
        Glide.with(itemView.context)
            .load(posterURL)
            .into(itemView.posterIV)

        itemView.setOnClickListener{

            val bundle = bundleOf("tvId" to tvShow.id)
            itemView.findNavController().navigate(R.id.action_tvListFragment_to_tvShowDetailsFragment,bundle)

        }
    }

}