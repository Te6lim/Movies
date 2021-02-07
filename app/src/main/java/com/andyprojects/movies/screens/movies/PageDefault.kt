package com.andyprojects.movies.screens.movies

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.andyprojects.movies.databinding.FragmentChildMoviesBinding
import com.andyprojects.movies.network.MoviesNetworkStatus
import kotlinx.android.synthetic.main.fragment_child_movies.view.*

interface PageDefault {

    fun setDefaults(
        viewModel: MoviesViewModel,
        viewLifecycleOwner: LifecycleOwner,
        binding: FragmentChildMoviesBinding,
    ) {
        binding.viewModel = viewModel
        binding.moviesRecyclerView.adapter = MoviesAdapter()

        refreshPage(binding, viewLifecycleOwner, viewModel)

        binding.connectionErrorScreen.retryButton.setOnClickListener {
            viewModel.getResponse()
            observeVariables(viewModel, viewLifecycleOwner, binding)
        }

        observeVariables(viewModel, viewLifecycleOwner, binding)
    }

    private fun refreshPage(binding: FragmentChildMoviesBinding, viewLifecycleOwner: LifecycleOwner, viewModel: MoviesViewModel) {
        binding.swiperRefresh.setOnRefreshListener {
            viewModel.getResponse()
            observeVariables(viewModel, viewLifecycleOwner, binding)
        }
    }

    private fun observeVariables(
        viewModel: MoviesViewModel,
        viewLifecycleOwner: LifecycleOwner,
        binding: FragmentChildMoviesBinding) {
        val recyclerView = binding.moviesRecyclerView
        val errorScreen = binding.connectionErrorScreen
        with(viewModel) {
            response.observe(viewLifecycleOwner, {
                (recyclerView.adapter as MoviesAdapter).submitList(it)
            })
            status.observe(viewLifecycleOwner, {
                when(it) {
                    MoviesNetworkStatus.ERROR -> {
                        binding.swiperRefresh.isRefreshing = false
                        if(!hasSomeLoaded) {
                            Toast.makeText(
                                errorScreen.context, "Network failed", Toast.LENGTH_SHORT
                            ).show()
                            recyclerView.visibility = View.GONE
                            errorScreen.visibility = View.VISIBLE
                        } else {
                            Toast.makeText(
                                errorScreen.context, "Network failed. Swipe down to refresh", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    MoviesNetworkStatus.LOADING -> {
                        binding.swiperRefresh.isRefreshing = true
                        errorScreen.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                    }

                    MoviesNetworkStatus.DONE -> {
                        binding.swiperRefresh.isRefreshing = false
                        errorScreen.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                    }
                    else -> {}
                }
            })
        }
    }
}