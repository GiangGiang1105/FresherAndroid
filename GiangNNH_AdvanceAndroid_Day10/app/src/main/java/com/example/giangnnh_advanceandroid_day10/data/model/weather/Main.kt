package com.example.giangnnh_advanceandroid_day10.data.model.weather

import androidx.core.text.HtmlCompat
import kotlin.math.roundToInt

data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
) {
    fun roundTemp(temp: Double): String = HtmlCompat.fromHtml(
        "${(temp - 273.15).roundToInt()}<sup>o</sup>C",
        HtmlCompat.FROM_HTML_MODE_LEGACY
    ).toString()
}