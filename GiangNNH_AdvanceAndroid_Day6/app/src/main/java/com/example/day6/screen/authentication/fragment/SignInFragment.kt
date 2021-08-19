package com.example.day6.screen.authentication.fragment

import android.view.LayoutInflater
import androidx.navigation.fragment.navArgs
import com.example.day6.base.BaseFragment
import com.example.day6.databinding.FragmentSignInBinding
import com.example.day6.screen.main.MainActivity

class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    private val args: SignInFragmentArgs by navArgs()

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentSignInBinding =
        FragmentSignInBinding.inflate(layoutInflater)

    override fun init() {
        binding.apply {
            phoneTextField.editText?.setText(args.phone)
            passwordTextField.editText?.setText(args.password)
            buttonSignIn.setOnClickListener {
                startActivity(MainActivity.newIntent(context))
            }
            buttonSignUp.setOnClickListener {
                val action = SignInFragmentDirections.actionSignInFragment2ToSignUpFragment2()
                controller.navigate(action)
            }
        }
    }
}