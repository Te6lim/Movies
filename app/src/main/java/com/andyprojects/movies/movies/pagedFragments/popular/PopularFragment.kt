package com.andyprojects.movies.movies.pagedFragments.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PopularFragment private constructor(): Fragment() {
    companion object {
        private var fragment: PopularFragment? = null
        fun newInstance(): PopularFragment {
            if(fragment == null) {
                fragment = PopularFragment()
            }
            return fragment as PopularFragment
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