package com.andyprojects.movies.movies.latest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentLatestBinding

class LatestFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLatestBinding = DataBindingUtil.
                inflate(inflater, R.layout.fragment_latest, container, false)
        binding.lifecycleOwner = this
        /*val latestViewModel = ViewModelProvider(this)
            .get(LatestViewModel::class.java)
        binding.viewModel = latestViewModel*/
        return binding.root
    }
}