package com.example.tpmsclient.view.fragment

import android.view.LayoutInflater
import com.example.tpmsclient.base.BaseFragment
import com.example.tpmsclient.databinding.FragmentConfigBinding

class ConfigFragment : BaseFragment<FragmentConfigBinding>() {

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentConfigBinding =
        FragmentConfigBinding.inflate(layoutInflater)

    override fun init() {

    }
}