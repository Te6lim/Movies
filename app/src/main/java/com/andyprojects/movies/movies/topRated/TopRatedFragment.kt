package com.andyprojects.movies.movies.topRated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentMoviesBinding
import com.andyprojects.movies.movies.PageDefault

class TopRatedFragment: Fragment(), PageDefault {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMoviesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movies,
                container, false)
        binding.lifecycleOwner = this

        val topRatedViewModel = ViewModelProvider(this)
            .get(TopRatedViewModel::class.java)

        setDefaults(topRatedViewModel, viewLifecycleOwner, binding)

        return binding.root
    }
}