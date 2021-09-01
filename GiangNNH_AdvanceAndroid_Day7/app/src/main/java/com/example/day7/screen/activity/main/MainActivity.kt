package com.example.day7.screen.activity.main

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.day6.base.BaseActivity
import com.example.day7.R
import com.example.day7.databinding.ActivityMainBinding
import com.example.day7.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var appBarConfig: AppBarConfiguration
    val viewModel: MainViewModel by viewModels()

    override fun getViewBinding(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun getNavHostFragment(): NavHostFragment =
        supportFragmentManager.findFragmentById(R.id.navigationMainFragment) as NavHostFragment

    override fun init() {
        binding.bottomNavigationView.setupWithNavController(controller)
        binding.bottomNavigationView.background = null
        appBarConfig =
            AppBarConfiguration(setOf(R.id.saleFragment, R.id.refundFragment, R.id.revenueFragment))
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(controller, appBarConfig)
        binding.buttonClear.setOnClickListener {
            clearDatabase()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    private fun clearDatabase() {
        lifecycleScope.launch {
            viewModel.clearDatabase()
        }
    }
}