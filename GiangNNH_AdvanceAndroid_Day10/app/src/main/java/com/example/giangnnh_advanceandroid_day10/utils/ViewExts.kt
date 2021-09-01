package com.example.giangnnh_advanceandroid_day10.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("set_url" )
fun loadImage(imageView: ImageView, icon: String?) {
    Log.e("TAG", "loadImage: $icon", )
    Glide.with(imageView).load("http://openweathermap.org/img/wn/$icon@2x.png").into(imageView)
}