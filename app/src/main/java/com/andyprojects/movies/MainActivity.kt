package com.andyprojects.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.andyprojects.movies.databinding.ActivityMainBinding
import com.andyprojects.movies.screens.discover.DiscoverFragment
import com.andyprojects.movies.screens.genres.GenresFragment
import com.andyprojects.movies.screens.movies.MoviesFragment
import com.andyprojects.movies.screens.settings.SettingsFragment
import com.andyprojects.movies.screens.tvShows.TvShowsFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val KEY = "N"
    }

    private lateinit var navDrawer: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var appToolbar: Toolbar

    private var searchBarIsVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        appToolbar = binding.appToolbar
        setSupportActionBar(appToolbar)

        navDrawer = binding.drawerLayout
        navView = binding.navView
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.movies -> {
                    switchHomeFragment(MoviesFragment(), it.itemId)
                }

                R.id.tvShows -> {
                    switchHomeFragment(TvShowsFragment(), it.itemId)
                }

                R.id.genres -> {
                    switchHomeFragment(GenresFragment(), it.itemId)
                }

                R.id.discover -> {
                    switchHomeFragment(DiscoverFragment(), it.itemId)
                }

                R.id.settings -> {
                    switchHomeFragment(SettingsFragment(), it.itemId)
                }
            }
            navDrawer.closeDrawer(GravityCompat.START)
            true
        }

        val toggle = ActionBarDrawerToggle(
            this, navDrawer, appToolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        navDrawer.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            switchHomeFragment(MoviesFragment(), R.string.title_movies)
            navView.setCheckedItem(R.id.movies)
        } else {
            navView.setCheckedItem(savedInstanceState.getInt(KEY))
            navView.checkedItem?.let {
                changeHomeTitle(it.itemId)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        navView.checkedItem?.let {
            outState.putInt(KEY, it.itemId)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (navDrawer.isDrawerOpen(GravityCompat.START))
            navDrawer.closeDrawer(GravityCompat.START)
        else super.onBackPressed()
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

    private fun switchHomeFragment(fragment: Fragment, itemId: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.home_fragment_container, fragment)
            .commit()
        changeHomeTitle(itemId)
    }

    private fun changeHomeTitle(itemId: Int) {
        supportActionBar?.let {
            title = when (itemId) {
                R.id.movies -> getString(R.string.title_movies)
                R.id.tvShows -> getString(R.string.title_tv_shows)
                R.id.genres -> getString(R.string.title_genres)
                R.id.discover -> getString(R.string.title_discover)
                R.id.settings -> getString(R.string.title_settings)
                else -> return
            }
        }
    }
}