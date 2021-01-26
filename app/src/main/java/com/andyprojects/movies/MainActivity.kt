package com.andyprojects.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
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
        binding.lifecycleOwner = this

        /**val app = this.application
        val viewModelFactory = MainViewModel.MainViewModelFactory(app)
        val mainViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
        binding.viewModel = mainViewModel**/

        appToolbar = binding.appToolbar
        setSupportActionBar(appToolbar)

        val navController = this.findNavController(R.id.navHost)
        navDrawer = binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this, navController, navDrawer)
        NavigationUI.setupWithNavController(binding.navView, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHost)
        return NavigationUI.navigateUp(navController, navDrawer)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}