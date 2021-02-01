package com.andyprojects.movies.movies.nowPlaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentMoviesBinding
import com.andyprojects.movies.movies.MoviesAdapter
import com.andyprojects.movies.movies.PageDefault

class NowPlayingFragment: Fragment(), PageDefault {

    private lateinit var recyclerView: RecyclerView
    private lateinit var retryButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMoviesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movies,
                container, false)
        binding.lifecycleOwner = this

        recyclerView = binding.moviesRecyclerView
        retryButton = binding.retryButton

        val errorScreen = binding.connectionErrorScreen

        recyclerView.adapter = MoviesAdapter()

        val nowPlayingViewModel = ViewModelProvider(this)
            .get(NowPlayingViewModel::class.java)
        binding.viewModel = nowPlayingViewModel

        setDefaults(
            nowPlayingViewModel,
            viewLifecycleOwner,
            recyclerView,
            errorScreen
        )

        return binding.root
    }
}