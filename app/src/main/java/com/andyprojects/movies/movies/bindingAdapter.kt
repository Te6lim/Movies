package com.andyprojects.movies.movies

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.andyprojects.movies.network.IMAGE_BASE_URL
import com.bumptech.glide.Glide

@BindingAdapter("moviePosterUrl")
fun ImageView.bindPoster(posterUrl: String?) {
    posterUrl?.let {
        val uri = (IMAGE_BASE_URL + posterUrl).toUri()
            .buildUpon().scheme("https").build()
        Glide.with(this)
            .load(uri)
            .into(this)
    }
}

@BindingAdapter("movieTitle")
fun TextView.bindTitle(title: String?) {
    title?.let {
        this.text = it
    }
}