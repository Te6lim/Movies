package com.andyprojects.movies.movies.upComing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentComingUpBinding
import com.andyprojects.movies.movies.MoviesAdapter
import com.andyprojects.movies.network.MoviesNetworkStatus

class UpComingFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentComingUpBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_coming_up,
                container, false)
        binding.lifecycleOwner = this

        recyclerView = binding.includeRecyclerView.moviesRecyclerView
        val errorScreen = binding.includeErrorScreen.connectionErrorScreen
        recyclerView.adapter = MoviesAdapter()
        val adapter = recyclerView.adapter as MoviesAdapter

        val upComingViewModel = ViewModelProvider(this)
            .get(UpComingViewModel::class.java)
        binding.viewModel = upComingViewModel

        with(upComingViewModel) {
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