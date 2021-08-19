package com.example.exercise.screen.main.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exercise.screen.main.fragment.AlarmFragment
import com.example.exercise.screen.main.fragment.MusicFragment

class MusicViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int) = when (position) {
        0 -> MusicFragment()
        1 -> AlarmFragment()
        else -> throw NotImplementedError("Fragment Not Found!!! ")
    }
}