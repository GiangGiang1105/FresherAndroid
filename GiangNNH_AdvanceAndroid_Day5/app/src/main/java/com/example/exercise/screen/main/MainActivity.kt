package com.example.exercise.screen.main

import android.app.AlarmManager
import android.view.LayoutInflater
import com.example.exercise.base.BaseActivity
import com.example.exercise.base.BaseRepository
import com.example.exercise.data.network.service.MusicApiService
import com.example.exercise.data.repository.MusicRepository
import com.example.exercise.databinding.ActivityMainBinding
import com.example.exercise.screen.main.adapter.MusicViewPagerAdapter
import com.example.exercise.screen.main.viewmodel.MainViewModel
import com.example.exercise.utils.NameTabItem
import com.google.android.material.tabs.TabLayoutMediator


/**
*
*
*Dạ em chào anh! Dạ, hiện tại app này của em có cả 2 phần bài tập là day 8 và day 9 ạ! Vì thấy 2 day
* rất giống nhau nên em đã làm trên cùng một app để tiết kiệm thời gian và để chỉnh sửa những chỗ
* không cần thiết cho tốt hơn. Nên mong anh chấm bài châm trước về trường hợp này ạ! Em cảm ơn anh nhiều ạ!
* */

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding, BaseRepository>() {

    private val listNameTabItem = listOf(NameTabItem.Music.name, NameTabItem.Alarm.name)
    lateinit var alarm: AlarmManager

    override fun init() {
        binding.viewPager.adapter = MusicViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = listNameTabItem[position]
        }.attach()
        alarm = getSystemService(ALARM_SERVICE) as AlarmManager
        viewModel.getAllMusic()
    }

    override fun getViewModel() = MainViewModel::class.java

    override fun getViewBinding(layoutInflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)

    override fun getRepository() =
        MusicRepository(musicRetrofit.retrofit(MusicApiService::class.java))
}