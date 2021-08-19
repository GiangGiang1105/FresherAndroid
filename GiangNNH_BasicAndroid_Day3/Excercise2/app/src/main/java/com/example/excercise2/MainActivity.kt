package com.example.excercise2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val arrayBundle = arrayListOf(
        "Hello!",
        "Hi!",
        "Salut!",
        "Hallo!",
        "Ciao!",
        "Ahoj!",
        "YAH sahs!",
        "Bog!",
        "Hej!",
        "Czesc!",
        "Ní hảo!",
        "Kon’nichiwa!",
        "Annyeonghaseyo!",
        "Shalom!",
        "Sah-wahd-deekah!",
        "Merhaba!",
        "Hujambo!",
        "Olá!"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeScreen.setOnClickListener {
            startActivity(MainActivity2.newIntent(this, arrayBundle))
        }
    }
}
