package com.andyprojects.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.*
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.andyprojects.movies.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val KEY = "K"
    }

    private lateinit var navDrawer: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var appToolbar: Toolbar

    private var searchBarIsVisible = false

    private var previousItem = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        navDrawer = binding.drawerLayout
        navView = binding.navView

        val navController = this.findNavController(R.id.home_fragment_container)

        appToolbar = binding.appToolbar
        val topLevelDestination = mutableSetOf(
            R.id.moviesFragment,
            R.id.tvShowsFragment,
            R.id.genresFragment,
            R.id.discoverFragment
        )
        val appBarConfiguration = AppBarConfiguration(topLevelDestination, navDrawer)
        setSupportActionBar(appToolbar)

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        previousItem = if (savedInstanceState != null) {
            val savedData = savedInstanceState.getInt(KEY)
            navView.setCheckedItem(savedData)
            savedData
        } else {
            navView.setCheckedItem(R.id.movies)
            R.id.movies
        }

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.movies -> {
                    setAsTopLevel(navController, R.id.moviesFragment)
                    previousItem = it.itemId
                }

                R.id.tvShows -> {
                    setAsTopLevel(navController, R.id.tvShowsFragment)
                    previousItem = it.itemId
                }

                R.id.genres -> {
                    setAsTopLevel(navController, R.id.genresFragment)
                    previousItem = it.itemId
                }

                R.id.discover -> {
                    setAsTopLevel(navController, R.id.discoverFragment)
                    previousItem = it.itemId
                }

                R.id.settings -> {
                    navigateToSettings(navController)
                }
            }
            navDrawer.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun setAsTopLevel(navController: NavController, id: Int) {
        val graph = navController.graph
        graph.startDestination = id
        navController.graph = graph
    }

    private fun navigateToSettings(navController: NavController) {
        when (previousItem) {
            R.id.movies ->
                navController.navigate(R.id.action_moviesFragment_to_settingsFragment)
            R.id.tvShowsFragment ->
                navController.navigate(R.id.action_tvShowsFragment_to_settingsFragment)
            R.id.genres ->
                navController.navigate(R.id.action_genresFragment_to_settingsFragment)
            R.id.discover ->
                navController.navigate(R.id.action_discoverFragment_to_settingsFragment)
        }
    }

    override fun onBackPressed() {
        if (navDrawer.isDrawerOpen(GravityCompat.START))
            navDrawer.closeDrawer(GravityCompat.START)
        else {
            navView.setCheckedItem(previousItem)
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        navView.checkedItem?.let {
            outState.putInt(KEY, it.itemId)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.home_fragment_container)
        navView.setCheckedItem(previousItem)
        return NavigationUI.navigateUp(navController, navDrawer)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.action_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val searchBar = this.appToolbar.search_bar
        return when (item.itemId) {
            R.id.search_option -> {
                if (searchBarIsVisible) {
                    searchBarIsVisible = false
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_search)
                    searchBar.visibility = View.GONE
                    true
                } else {
                    searchBarIsVisible = true
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_close)
                    searchBar.visibility = View.VISIBLE
                    true
                }
            }

            R.id.settings -> {
                navView.checkedItem?.let {
                    if (it.itemId != item.itemId)
                        navigateToSettings(findNavController(R.id.home_fragment_container))
                }
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}