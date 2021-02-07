package com.andyprojects.movies.screens.tvShows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentShowsTvBinding

class TvShowsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShowsTvBinding =
            DataBindingUtil
                .inflate(inflater, R.layout.fragment_shows_tv, container, false)
        return binding.root
    }
}