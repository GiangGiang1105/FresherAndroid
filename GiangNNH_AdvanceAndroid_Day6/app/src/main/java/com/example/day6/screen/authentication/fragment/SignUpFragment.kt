package com.example.day6.screen.authentication.fragment

import android.view.LayoutInflater
import com.example.day6.base.BaseFragment
import com.example.day6.databinding.FragmentSignUpBinding

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentSignUpBinding =
        FragmentSignUpBinding.inflate(layoutInflater)

    override fun init() {
        binding.buttonSignUp.setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragment2ToProfileInfoFragment2()
            controller.navigate(action)
        }
        binding.buttonLogin.setOnClickListener {
            val phone = binding.phoneTextField.editText?.text.toString()
            val password = binding.passwordTextField.editText?.text.toString()
            val action =
                SignUpFragmentDirections.actionSignUpFragment2ToSignInFragment2(phone, password)
            controller.navigate(action)
        }
    }
}