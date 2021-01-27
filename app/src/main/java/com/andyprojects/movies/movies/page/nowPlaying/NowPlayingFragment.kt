package com.andyprojects.movies.movies.page.nowPlaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentPlayingNowBinding
import com.andyprojects.movies.movies.MoviesAdapter

class NowPlayingFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlayingNowBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_playing_now,
                container, false)
        binding.lifecycleOwner = this

        recyclerView = binding.moviesRecyclerView
        recyclerView.adapter = MoviesAdapter()
        val adapter = recyclerView.adapter as MoviesAdapter

        val nowPlayingViewModel = ViewModelProvider(this)
            .get(NowPlayingViewModel::class.java)
        binding.viewModel = nowPlayingViewModel

        nowPlayingViewModel.response.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        return binding.root
    }
}