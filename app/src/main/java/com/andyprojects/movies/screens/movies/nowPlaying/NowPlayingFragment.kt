package com.andyprojects.movies.screens.movies.nowPlaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentChildMoviesBinding
import com.andyprojects.movies.screens.movies.PageDefault

class NowPlayingFragment: Fragment(), PageDefault {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentChildMoviesBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_child_movies, container, false)
        binding.lifecycleOwner = this

        val nowPlayingViewModel = ViewModelProvider(this)
            .get(NowPlayingViewModel::class.java)

        setDefaults(nowPlayingViewModel, viewLifecycleOwner, binding)

        return binding.root
    }
}