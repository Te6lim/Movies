package com.andyprojects.movies.movies.upComing

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
import com.andyprojects.movies.network.MoviesNetworkStatus

class UpComingFragment: Fragment(), PageDefault {

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

        val upComingViewModel = ViewModelProvider(this)
            .get(UpComingViewModel::class.java)
        binding.viewModel = upComingViewModel

        setDefaults(
            upComingViewModel,
            viewLifecycleOwner,
            recyclerView,
            errorScreen
        )

        return binding.root
    }
}