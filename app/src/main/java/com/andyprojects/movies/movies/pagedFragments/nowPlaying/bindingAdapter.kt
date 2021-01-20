package com.andyprojects.movies.movies.pagedFragments.nowPlaying

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("response")
fun bindResponse(view: TextView, response: String?) {
    response?.let {
        view.text = it
    }
}