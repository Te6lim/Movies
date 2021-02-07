package com.andyprojects.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.andyprojects.movies.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var navDrawer: DrawerLayout
    private lateinit var appToolbar: Toolbar

    private var searchBarIsVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        navDrawer = binding.drawerLayout

        appToolbar = binding.appToolbar
        setSupportActionBar(appToolbar)

        val navView: NavigationView = binding.navView

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