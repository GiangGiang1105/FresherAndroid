package com.example.day6.screen.authentication.fragment

import android.util.Log
import android.view.LayoutInflater
import com.example.day6.databinding.FragmentAuthBinding
import com.example.day6.R
import com.example.day6.base.BaseFragment

class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentAuthBinding =
        FragmentAuthBinding.inflate(layoutInflater)

    override fun init() {
        binding.buttonLogin.setOnClickListener {
            val action = AuthFragmentDirections.actionAuthFragment2ToSignInFragment2("", "")
            controller.navigate(action)
        }
        binding.buttonSignUp.setOnClickListener {
            val action = AuthFragmentDirections.actionAuthFragment2ToSignUpFragment2()
            controller.navigate(action)
        }
    }
}