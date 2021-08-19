package com.example.day6.screen.main.fragment

import android.view.LayoutInflater
import com.example.day6.base.BaseFragment
import com.example.day6.databinding.FragmentHomeBinding

class ListFriendFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun init() {}

}