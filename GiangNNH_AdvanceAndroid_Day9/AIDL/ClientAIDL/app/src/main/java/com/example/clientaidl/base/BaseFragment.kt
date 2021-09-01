package com.example.clientaidl.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.NavController
import androidx.viewbinding.ViewBinding
import com.example.clientaidl.viewmodel.MainViewModel

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected lateinit var controller: NavController
    protected lateinit var binding: VB
    protected val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(layoutInflater)
        controller = findNavController()
        init()
        return binding.root
    }

    abstract fun getViewBinding(layoutInflater: LayoutInflater): VB

    abstract fun init()
}