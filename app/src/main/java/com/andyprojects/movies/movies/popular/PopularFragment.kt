package com.andyprojects.movies.movies.popular

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
import com.andyprojects.movies.network.MoviesNetworkStatus

class PopularFragment: Fragment() {

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
        val adapter = recyclerView.adapter as MoviesAdapter

        val popularViewModel = ViewModelProvider(this)
            .get(PopularViewModel::class.java)
        binding.viewModel = popularViewModel

        with(popularViewModel) {
            response.observe(viewLifecycleOwner, {
                adapter.submitList(it)
            })
            status.observe(viewLifecycleOwner, {
                when(it) {
                    MoviesNetworkStatus.ERROR -> {
                        recyclerView.visibility = View.GONE
                        errorScreen.visibility = View.VISIBLE
                    }
                    MoviesNetworkStatus.DONE -> {
                        errorScreen.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                    }
                    else -> {}
                }
            })
        }

        return binding.root
    }
}