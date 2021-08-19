package com.example.giangnnh_advanceandroid_day4.screen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.giangnnh_advanceandroid_day4.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {

    private lateinit var informationBinding: FragmentInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        informationBinding = FragmentInformationBinding.inflate(inflater, container, false)
        return informationBinding.root
    }
}