package com.example.tpmsclient.view.fragment

import android.view.LayoutInflater
import com.example.tpmsclient.base.BaseFragment
import com.example.tpmsclient.databinding.FragmentObserverBinding

class ObserverFragment : BaseFragment<FragmentObserverBinding>() {

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentObserverBinding =
        FragmentObserverBinding.inflate(layoutInflater)

    override fun init() {

    }
}