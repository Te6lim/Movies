package com.andyprojects.movies.movies.pagedFragments.topRated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class TopRatedFragment private constructor(): Fragment() {
    companion object {
        private var fragment: TopRatedFragment? = null
        fun newInstance(): TopRatedFragment {
            if(fragment == null) {
                fragment = TopRatedFragment()
            }
            return fragment as TopRatedFragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}