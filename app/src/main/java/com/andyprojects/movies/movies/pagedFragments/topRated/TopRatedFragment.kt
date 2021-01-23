package com.andyprojects.movies.movies.pagedFragments.topRated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentRatedTopBinding

class TopRatedFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRatedTopBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_rated_top,
                container, false)
        binding.lifecycleOwner = this
        /*val topRatedViewModel = ViewModelProvider(this)
            .get(TopRatedViewModel::class.java)
        binding.viewModel = topRatedViewModel*/
        return binding.root
    }
}