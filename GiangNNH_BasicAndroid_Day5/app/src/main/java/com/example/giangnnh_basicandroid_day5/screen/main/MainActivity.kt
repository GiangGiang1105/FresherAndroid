package com.example.giangnnh_basicandroid_day5.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.giangnnh_basicandroid_day5.R
import com.example.giangnnh_basicandroid_day5.databinding.ActivityMainBinding
import com.example.giangnnh_basicandroid_day5.screen.fragment.AFragment
import com.example.giangnnh_basicandroid_day5.viewmodel.StudentViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    lateinit var studentViewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeFragmentView(AFragment())
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        setContentView(mainBinding.root)
    }

    fun changeFragmentView(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment::class.java, null)
            addToBackStack(fragment.javaClass.name.toString())
            commit()
        }
        supportFragmentManager.executePendingTransactions()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) finish()
        if (count == 1) mainBinding.buttonChangeFrame.setText(R.string.text_button_add_new_student)
    }
}