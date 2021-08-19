package com.example.excercise2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val arrayBundle = intent.getStringArrayListExtra(BUNDLE_ARRAY).toString()
        textIntent.text = arrayBundle
    }

    companion object {

        private const val BUNDLE_ARRAY = "BUNDLE_ARRAY"
        private const val TYPE = "text/plain"

        fun newIntent(context: Context, arrayBundle: ArrayList<String>?) =
            Intent(context, MainActivity2::class.java).apply {
                putStringArrayListExtra(BUNDLE_ARRAY, arrayBundle)
                type = TYPE
            }
    }
}
