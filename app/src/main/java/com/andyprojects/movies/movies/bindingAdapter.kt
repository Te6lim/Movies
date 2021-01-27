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
        if(title.length > 12)
            this.text = truncateTitle(it)
        else this.text = title
    }
}

fun truncateTitle(title: String): String {
    val newTitle = StringBuffer()
    for(c in 0..9)
        newTitle.append(title[c])
    newTitle.append("...")
    return newTitle.toString()
}