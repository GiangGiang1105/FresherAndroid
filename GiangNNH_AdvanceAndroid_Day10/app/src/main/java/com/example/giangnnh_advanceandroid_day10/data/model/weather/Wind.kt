package com.example.giangnnh_advanceandroid_day10.data.model.weather

import kotlin.math.roundToInt

data class Wind(
    val deg: Int,
    val speed: Double
) {
    fun getSpeed(speed: Double): String = (speed * 3.6).roundToInt().toString()
}