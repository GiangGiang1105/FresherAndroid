package com.example.giangnnh_advanceandroid_day4.screen.activity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import com.example.giangnnh_advanceandroid_day4.R
import com.example.giangnnh_advanceandroid_day4.base.BaseActivity
import com.example.giangnnh_advanceandroid_day4.data.network.ICategory
import com.example.giangnnh_advanceandroid_day4.data.repository.CategoryRepository
import com.example.giangnnh_advanceandroid_day4.databinding.ActivityMainBinding
import com.example.giangnnh_advanceandroid_day4.screen.adapter.MainViewPagerAdapter
import com.example.giangnnh_advanceandroid_day4.utils.NameTabItem
import com.example.giangnnh_advanceandroid_day4.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding, CategoryRepository>() {

    private val listNameItemTabLayout =
        listOf(NameTabItem.Vertical.name, NameTabItem.Horizontal.name, NameTabItem.Information.name)

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun getRepository(): CategoryRepository =
        CategoryRepository.getInstance(retrofit.getRetrofit(ICategory::class.java))

    override fun getViewBinding(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun init() {
        initViewPager()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initViewPager() {
        binding.viewPager.apply {
            adapter = MainViewPagerAdapter(this@MainActivity)
            isUserInputEnabled = false
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = listNameItemTabLayout[position]
            when (position) {
                0 -> tab.icon = getDrawable(R.drawable.ic_food)
                1 -> tab.icon = getDrawable(R.drawable.ic_food_apple_outline)
                2 -> tab.icon = getDrawable(R.drawable.ic_hamburger)
                else -> throw NotImplementedError("Not found $position")
            }
        }.attach()
    }
}