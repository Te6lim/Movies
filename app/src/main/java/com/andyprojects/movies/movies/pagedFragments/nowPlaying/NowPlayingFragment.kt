package com.andyprojects.movies.movies.pagedFragments.nowPlaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentPlayingNowBinding

class NowPlayingFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlayingNowBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_playing_now,
                container, false)
        binding.lifecycleOwner = this
        val nowPlayingViewModel = ViewModelProvider(this)
            .get(NowPlayingViewModel::class.java)
        binding.viewModel = nowPlayingViewModel
        return binding.root
    }
}