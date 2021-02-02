package com.andyprojects.movies.movies

import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.andyprojects.movies.network.MoviesNetworkStatus
import kotlinx.android.synthetic.main.fragment_movies.view.*

interface PageDefault {

    fun setDefaults(
        viewModel: MoviesViewModel,
        viewLifecycleOwner: LifecycleOwner,
        recyclerView: RecyclerView,
        errorScreen: ConstraintLayout
    ) {

        errorScreen.retryButton.setOnClickListener {
            viewModel.getResponse()
            observeVariables(
                viewModel,
                viewLifecycleOwner,
                recyclerView,
                errorScreen
            )
        }

        observeVariables(
            viewModel,
            viewLifecycleOwner,
            recyclerView,
            errorScreen
        )
    }

    private fun observeVariables(
        viewModel: MoviesViewModel,
        viewLifecycleOwner: LifecycleOwner,
        recyclerView: RecyclerView,
        errorScreen: ConstraintLayout) {
        with(viewModel) {
            response.observe(viewLifecycleOwner, {
                (recyclerView.adapter as MoviesAdapter).submitList(it)
            })
            status.observe(viewLifecycleOwner, {
                when(it) {
                    MoviesNetworkStatus.ERROR -> {
                        if(hasSomeLoaded == false) {
                            recyclerView.visibility = View.GONE
                            errorScreen.visibility = View.VISIBLE
                            Toast.makeText(
                                errorScreen.context, "Network failed", Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                errorScreen.context, "Network failed. Swipe down to refresh", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    MoviesNetworkStatus.LOADING -> {
                        errorScreen.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                    }

                    MoviesNetworkStatus.DONE -> {
                        errorScreen.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                    }
                    else -> {}
                }
            })
        }
    }
}