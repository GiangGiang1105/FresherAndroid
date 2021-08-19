package com.example.giangnnh_advanceandroid_day4.utils

import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.giangnnh_advanceandroid_day4.R
import com.example.giangnnh_advanceandroid_day4.data.model.Category
import com.example.giangnnh_advanceandroid_day4.screen.fragment.DialogCustom

@BindingAdapter("set_url")
fun loadImage(img: ImageView, link: String) {
    Glide.with(img).load(link)
        .placeholder(R.drawable.ic_food)
        .error(R.drawable.ic_error)
        .into(img)
}

@BindingAdapter( "statusHeight")
fun setHeight(view: View, boolean: Boolean) {
    if (boolean) {
        view.layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
    }
}

fun Fragment.showToast(message: String) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    ).show()
}

fun Fragment.showDialog(category: Category) {
    DialogCustom.newInstance(category).show(
        parentFragmentManager,
        "DialogCustom"
    )
}