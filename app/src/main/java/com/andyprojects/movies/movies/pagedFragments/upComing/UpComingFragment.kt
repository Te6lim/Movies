package com.andyprojects.movies.movies.pagedFragments.upComing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentComingUpBinding

class UpComingFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentComingUpBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_coming_up,
                container, false)
        binding.lifecycleOwner = this
        val upComingViewModel = ViewModelProvider(this)
            .get(UpComingViewModel::class.java)
        binding.viewModel = upComingViewModel
        return binding.root
    }
}