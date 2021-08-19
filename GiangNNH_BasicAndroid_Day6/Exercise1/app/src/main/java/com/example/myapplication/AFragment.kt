package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.myapplication.databinding.FragmentABinding
import com.example.myapplication.utils.Constant

class AFragment : Fragment(R.layout.fragment_a) {

    private lateinit var dataBinding: FragmentABinding
    private val resultBroadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                if (it.hasExtra(Constant.BUNDLE_ERROR_DEVIDE)) {
                    Toast.makeText(
                        context,
                        it.getStringExtra(Constant.BUNDLE_ERROR_DEVIDE),
                        Toast.LENGTH_LONG
                    ).show()
                }
                if (it.hasExtra(Constant.BUNDLE_RESULT)) {
                    dataBinding.textResult.text =
                        it.getIntExtra(Constant.BUNDLE_RESULT, 0)
                            .toString()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentABinding.inflate(layoutInflater, container, false)
        return dataBinding.root
    }

    override fun onResume() {
        super.onResume()
        registerBroadCastCalculate()
    }

    override fun onPause() {
        super.onPause()
        unregisterBroadCastCalculate()
    }

    private fun registerBroadCastCalculate() {
        LocalBroadcastManager.getInstance(requireActivity())
            .registerReceiver(resultBroadCastReceiver, IntentFilter(Constant.ACTION_RESULT_INTENT))
    }

    private fun unregisterBroadCastCalculate() {
        LocalBroadcastManager.getInstance(requireActivity())
            .unregisterReceiver(resultBroadCastReceiver)
    }
}