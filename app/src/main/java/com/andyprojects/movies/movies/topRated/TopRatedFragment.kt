package com.andyprojects.movies.movies.topRated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentRatedTopBinding
import com.andyprojects.movies.movies.MoviesAdapter

class TopRatedFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRatedTopBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_rated_top,
                container, false)
        binding.lifecycleOwner = this

        recyclerView = binding.includeRecyclerView.moviesRecyclerView
        recyclerView.adapter = MoviesAdapter()
        val adapter = recyclerView.adapter as MoviesAdapter

        val topRatedViewModel = ViewModelProvider(this)
            .get(TopRatedViewModel::class.java)
        binding.viewModel = topRatedViewModel

            topRatedViewModel.response.observe(viewLifecycleOwner, {
                adapter.submitList(it)
            })

        return binding.root
    }
}