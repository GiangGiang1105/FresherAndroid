package com.example.clientstudent.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.clientstudent.R
import com.example.clientstudent.databinding.ActivityMainBinding
import com.example.clientstudent.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * link github library: https://github.com/GiangGiang1105/AIDLStudent-library
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private lateinit var controller: NavController
    private lateinit var appBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navHostFrament =
            supportFragmentManager.findFragmentById(R.id.navigationMainFragment) as NavHostFragment
        controller = navHostFrament.findNavController()
        appBarConfig = AppBarConfiguration(
            setOf(R.id.studentsFragment)
        )
        lifecycle.addObserver(viewModel)
        setupActionBarWithNavController(controller, appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addStudent -> {
                controller.navigate(R.id.insertFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}