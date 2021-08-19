package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.databinding.FragmentCalculateBinding
import com.example.myapplication.utils.Constant

class CalculateFragment : Fragment(R.layout.fragment_calculate) {

    lateinit var calculateBinding: FragmentCalculateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        calculateBinding = FragmentCalculateBinding.inflate(layoutInflater, container, false)
        buttonOnClick()
        return calculateBinding.root
    }


    private val onClickListener = View.OnClickListener {
        var a = 0
        var b = 0
        if (calculateBinding.editTextA.text.toString()
                .isNotEmpty() && calculateBinding.editTextB.text.toString().isNotEmpty()
        ) {
            a = calculateBinding.editTextA.text.toString().toInt()
            b = calculateBinding.editTextB.text.toString().toInt()
        } else {
            Toast.makeText(context, "Add full field before when calculate!", Toast.LENGTH_LONG)
                .show()
        }

        when (it.id) {
            R.id.buttonAdd -> (activity as MainActivity).mService.calculator(a, b, Constant.PLUS)
            R.id.buttonMulti -> (activity as MainActivity).mService.calculator(a, b, Constant.MULTI)
            R.id.buttonMinus -> (activity as MainActivity).mService.calculator(a, b, Constant.MINUS)
            R.id.buttonDivide -> (activity as MainActivity).mService.calculator(
                a,
                b,
                Constant.DIVIDE
            )
        }
    }

    private fun buttonOnClick() {
        calculateBinding.apply {
            buttonAdd.setOnClickListener(onClickListener)
            buttonMinus.setOnClickListener(onClickListener)
            buttonDivide.setOnClickListener(onClickListener)
            buttonMulti.setOnClickListener(onClickListener)
        }
    }

}