package com.andyprojects.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.andyprojects.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navDrawer: DrawerLayout
    private lateinit var appToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        appToolbar = binding.appToolbar
        setSupportActionBar(appToolbar)
        val navController = this.findNavController(R.id.navHost)
        navDrawer = binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this, navController, navDrawer)
        NavigationUI.setupWithNavController(binding.navView, navController)

        navController.addOnDestinationChangedListener {
                nc: NavController, nd: NavDestination, _: Bundle? ->
            if(nd.id == nc.graph.startDestination)
                navDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            else navDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHost)
        return NavigationUI.navigateUp(navController, navDrawer)
    }
}