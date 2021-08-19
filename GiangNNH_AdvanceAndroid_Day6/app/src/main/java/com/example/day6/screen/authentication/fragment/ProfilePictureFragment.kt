package com.example.day6.screen.authentication.fragment

import android.view.LayoutInflater
import com.example.day6.base.BaseFragment
import com.example.day6.databinding.FragmentProfilePictureBinding
import com.example.day6.screen.main.MainActivity

class ProfilePictureFragment : BaseFragment<FragmentProfilePictureBinding>() {

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentProfilePictureBinding =
        FragmentProfilePictureBinding.inflate(layoutInflater)

    override fun init() {
        binding.buttonSkip.setOnClickListener {
            startActivity(MainActivity.newIntent(context))
        }
        binding.buttonFinish.setOnClickListener {
            startActivity(MainActivity.newIntent(context))
        }
    }
}