package com.andyprojects.movies.movies

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("moviePosterUrl")
fun ImageView.bindPoster(posterUrl: String?) {

}

@BindingAdapter("movieTitle")
fun TextView.bindTitle(title: String?) {
    title?.let {
        this.text = title
    }
}