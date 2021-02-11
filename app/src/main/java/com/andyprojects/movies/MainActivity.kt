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
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.andyprojects.movies.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var navDrawer: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var appToolbar: Toolbar

    private var searchBarIsVisible = false

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

        navView.setNavigationItemSelectedListener {
            val graph = navController.graph
            when (it.itemId) {
                R.id.movies -> {
                    graph.startDestination = R.id.moviesFragment
                    navController.graph = graph
                }

                R.id.tvShows -> {
                    graph.startDestination = R.id.tvShowsFragment
                    navController.graph = graph
                }

                R.id.genres -> {
                    graph.startDestination = R.id.genresFragment
                    navController.graph = graph
                }

                R.id.discover -> {
                    graph.startDestination = R.id.discoverFragment
                    navController.graph = graph
                }

                R.id.settings -> {
                }
            }
            navDrawer.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.home_fragment_container)
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
            else -> return super.onOptionsItemSelected(item)
        }
    }
}