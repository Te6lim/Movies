package com.andyprojects.movies.screens.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.andyprojects.movies.R
import com.andyprojects.movies.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSettingsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        return binding.root
    }
}