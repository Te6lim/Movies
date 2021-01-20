package com.andyprojects.movies.movies.pagedFragments.latest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class LatestFragment private constructor(): Fragment() {
    companion object {
        private var fragment: LatestFragment? = null
        fun newInstance(): LatestFragment {
            if(fragment == null) {
                fragment = LatestFragment()
            }
            return fragment as LatestFragment
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