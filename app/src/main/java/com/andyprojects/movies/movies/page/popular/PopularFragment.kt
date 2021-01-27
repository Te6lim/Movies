package com.andyprojects.movies.movies.page.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentPopularBinding

class PopularFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPopularBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_popular,
                container, false)
        binding.lifecycleOwner = this
        /*val popularViewModel = ViewModelProvider(this)
            .get(PopularViewModel::class.java)
        binding.viewModel = popularViewModel*/
        return binding.root
    }
}