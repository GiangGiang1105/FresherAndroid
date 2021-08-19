package com.example.giangnnh_advanceandroid_day4.screen.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.giangnnh_advanceandroid_day4.screen.fragment.CategoryAFragment
import com.example.giangnnh_advanceandroid_day4.screen.fragment.CategoryFragment
import com.example.giangnnh_advanceandroid_day4.screen.fragment.InformationFragment

class MainViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> CategoryFragment()
            1 -> CategoryAFragment()
            2 -> InformationFragment()
            else -> throw NotImplementedError("Not found fragment with position $position")
        }
}