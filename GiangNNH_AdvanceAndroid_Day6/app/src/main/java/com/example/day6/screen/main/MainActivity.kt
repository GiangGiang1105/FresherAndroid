package com.example.day6.screen.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.day6.R
import com.example.day6.base.BaseActivity
import com.example.day6.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var appBarConfig: AppBarConfiguration


    override fun getViewBinding(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun getNavHostFragment(): NavHostFragment =
        supportFragmentManager.findFragmentById(R.id.navigationMainFragment) as NavHostFragment

    override fun init() {
        appBarConfig = AppBarConfiguration(
            setOf(R.id.messageFragment, R.id.homeFragment),
            drawerLayout = binding.drawer
        )
        binding.navView.background.alpha = 100
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(controller, appBarConfig)
        binding.navView.setupWithNavController(controller)
        binding.bottomNav.setupWithNavController(controller)
    }

    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    companion object {
        fun newIntent(context: Context?) = Intent(context, MainActivity::class.java)
    }
}