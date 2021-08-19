package com.example.day6.screen.main.fragment

import android.view.LayoutInflater
import com.example.day6.base.BaseFragment
import com.example.day6.databinding.FragmentMessageBinding

class MessageFragment : BaseFragment<FragmentMessageBinding>() {

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentMessageBinding =
        FragmentMessageBinding.inflate(layoutInflater)

    override fun init() {}

}