package com.andyprojects.movies.movies.pagedFragments.upComing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class UpComingFragment private constructor(): Fragment() {
    companion object {
        private var fragment: UpComingFragment? = null
        fun newInstance(): UpComingFragment {
            if(fragment == null) {
                fragment = UpComingFragment()
            }
            return fragment as UpComingFragment
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