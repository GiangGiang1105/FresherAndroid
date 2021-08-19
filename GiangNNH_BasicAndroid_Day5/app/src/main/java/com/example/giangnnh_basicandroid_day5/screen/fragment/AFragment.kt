package com.example.giangnnh_basicandroid_day5.screen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.giangnnh_basicandroid_day5.R
import com.example.giangnnh_basicandroid_day5.databinding.FragmentABinding
import com.example.giangnnh_basicandroid_day5.screen.adapter.StudentAdapter
import com.example.giangnnh_basicandroid_day5.screen.main.MainActivity
import com.example.giangnnh_basicandroid_day5.viewmodel.StudentViewModel

class AFragment : Fragment(R.layout.fragment_a) {

    private lateinit var aBinding: FragmentABinding
    private lateinit var viewModel: StudentViewModel
    private val studentAdapter by lazy {
        StudentAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        aBinding = FragmentABinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).studentViewModel
        initView()
        return aBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).apply {
            mainBinding.buttonChangeFrame.setOnClickListener {
                changeFragmentView(BFragment())
                mainBinding.buttonChangeFrame.setText(R.string.text_button_save_student)
            }
        }
        updateView()
    }

    private fun initView() {
        aBinding.resultRecyclerView.apply {
            setHasFixedSize(false)
            adapter = studentAdapter
        }
    }

    private fun updateView() {
        studentAdapter.updateData(viewModel.listStudent)
    }
}