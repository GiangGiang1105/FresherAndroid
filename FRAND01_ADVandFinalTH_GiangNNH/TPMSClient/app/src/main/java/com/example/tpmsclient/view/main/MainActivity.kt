package com.example.tpmsclient.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tpmsclient.R
import com.example.tpmsclient.databinding.ActivityMainBinding
import com.example.tpmsclient.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigationMainFragment) as NavHostFragment
        controller = navHostFragment.findNavController()
        appBarConfig = AppBarConfiguration(
            setOf(R.id.observerFragment, R.id.configFragment),
            drawerLayout = binding.drawer
        )
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(controller, appBarConfig)
        binding.navView.setupWithNavController(controller)
    }

    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

}