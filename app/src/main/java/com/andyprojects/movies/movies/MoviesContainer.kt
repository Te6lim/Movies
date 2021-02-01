package com.andyprojects.movies.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.ContainerMoviesBinding
import com.andyprojects.movies.movies.latest.LatestFragment
import com.andyprojects.movies.movies.nowPlaying.NowPlayingFragment
import com.andyprojects.movies.movies.popular.PopularFragment
import com.andyprojects.movies.movies.topRated.TopRatedFragment
import com.andyprojects.movies.movies.upComing.UpComingFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MoviesContainer: Fragment() {
    private lateinit var moviesPager: ViewPager2
    private lateinit var fragmentAdapter: MoviesFragmentAdapter
    private lateinit var binding: ContainerMoviesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.container_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentAdapter = MoviesFragmentAdapter(this)
        moviesPager = binding.moviesPager
        moviesPager.offscreenPageLimit = 5
        moviesPager.adapter = fragmentAdapter
        val tabLayout = binding.moviesTabLayout
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        TabLayoutMediator(tabLayout, moviesPager) { tab, position ->
            tab.text = when(position) {
                0 -> resources.getString(R.string.title_now_playing)
                1 -> resources.getString(R.string.title_popular)
                2 -> resources.getString(R.string.title_top_rated)
                3 -> resources.getString(R.string.title_latest)
                4 -> resources.getString(R.string.title_up_coming)
                else -> ""
            }
        }.attach()
    }

    private inner class MoviesFragmentAdapter(fragment: Fragment)
        : FragmentStateAdapter(fragment) {
        override fun getItemCount() = 5

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> NowPlayingFragment()
                1 -> PopularFragment()
                2 -> TopRatedFragment()
                3 -> LatestFragment()
                else -> UpComingFragment()
            }
        }
    }
}