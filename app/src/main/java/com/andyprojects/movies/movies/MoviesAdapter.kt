package com.andyprojects.movies.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.ItemMovieBinding
import kotlin.math.floor

class MoviesAdapter: PagedListAdapter<Movie, MovieViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding = MovieViewHolder.from(parent)
        adaptViewDimension(itemMovieBinding, parent)
        return MovieViewHolder(itemMovieBinding)
    }

    private fun adaptViewDimension(
        itemMovieBinding: ItemMovieBinding,
        parent: ViewGroup
    ) {
        val params = itemMovieBinding.root.layoutParams
        val fullWidth = (parent.parent as ViewGroup).width
        with(params) {
            val largeWidth = fullWidth / 3
            width = largeWidth - floor(0.1 * largeWidth).toInt()
            height = (width * 1.6).toInt()
        }
        itemMovieBinding.root.layoutParams = params
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let{holder.bind(it)}
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie)
                = oldItem == newItem

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie)
                = oldItem.title == newItem.title
    }
}

class MovieViewHolder(private val movieHolder: ItemMovieBinding)
    : RecyclerView.ViewHolder(movieHolder.root) {
    companion object {
        fun from(parent: ViewGroup): ItemMovieBinding {
            val inflater = LayoutInflater.from(parent.context)
            return DataBindingUtil
                .inflate(inflater, R.layout.item_movie, parent, false)
        }
    }
    fun bind(movie: Movie) {
        movieHolder.movie = movie
    }
}