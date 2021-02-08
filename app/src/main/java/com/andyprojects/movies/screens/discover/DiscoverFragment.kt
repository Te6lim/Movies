package com.andyprojects.movies.screens.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentDiscoverBinding

class DiscoverFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDiscoverBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false)
        return binding.root
    }
}