package com.example.day6.screen.authentication

import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import com.example.day6.R
import com.example.day6.base.BaseActivity
import com.example.day6.databinding.ActivityAuthBinding

class AuthActivity : BaseActivity<ActivityAuthBinding>() {

    override fun getViewBinding(layoutInflater: LayoutInflater): ActivityAuthBinding =
        ActivityAuthBinding.inflate(layoutInflater)

    override fun getNavHostFragment(): NavHostFragment =
        supportFragmentManager.findFragmentById(R.id.navigationAuthFragment) as NavHostFragment

    override fun init() {}
}