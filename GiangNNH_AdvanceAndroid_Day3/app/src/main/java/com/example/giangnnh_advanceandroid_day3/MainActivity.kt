package com.example.giangnnh_advanceandroid_day3

import android.animation.*
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import com.example.giangnnh_advanceandroid_day3.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private val viewOnClickListener = View.OnClickListener { v ->
        when (v?.id) {
            R.id.buttonRotate -> {
                rotateStar(mainBinding.imageStar, -360f, 0f).also {
                    it.repeatCount = 1
                    startAnimation(it, mainBinding.buttonRotate)
                }
            }
            R.id.buttonTranslate -> {
                translateStar(mainBinding.imageStar, "translationX", -400f, 400f).also {
                    it.repeatCount = 1
                    startAnimation(it, mainBinding.buttonTranslate)
                }
            }
            R.id.buttonScale -> {
                scaleStar(mainBinding.imageStar, 2f).also {
                    it.repeatCount = 1
                    startAnimation(it, mainBinding.buttonScale)
                }
            }
            R.id.buttonFade -> {
                fadeStar(mainBinding.imageStar).also {
                    it.repeatCount = 1
                    startAnimation(it, mainBinding.buttonFade)
                }
            }
            R.id.buttonSkyColor -> {
                skyColor().also {
                    startAnimation(it, mainBinding.buttonSkyColor)
                }
            }
            R.id.buttonShower -> {
                showerStar().also { it ->
                    //startAnimation(it, mainBinding.buttonShower)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        handleEventAnimationWhenClickButton()
    }

    private fun handleEventAnimationWhenClickButton() {
        mainBinding.apply {
            viewOnClickListener.also {
                buttonRotate.setOnClickListener(it)
                buttonTranslate.setOnClickListener(it)
                buttonScale.setOnClickListener(it)
                buttonFade.setOnClickListener(it)
                buttonSkyColor.setOnClickListener(it)
                buttonShower.setOnClickListener(it)
            }
        }

    }

    private fun rotateStar(view: View, vararg value1: Float) =
        ObjectAnimator.ofFloat(view, View.ROTATION, *value1).apply {
            duration = 7000L
            interpolator = AccelerateDecelerateInterpolator()
        }

    private fun translateStar(view: View, property: String, vararg value: Float) =
        ObjectAnimator.ofFloat(view, property, *value)
            .apply {
                duration = 7000L
                repeatMode = ObjectAnimator.REVERSE
                interpolator = OvershootInterpolator()
            }

    private fun scaleStar(view: View, vararg value: Float): ObjectAnimator {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, *value)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, *value)
        return ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY).apply {
            duration = 7000L
            repeatMode = ObjectAnimator.REVERSE
        }
    }

    private fun fadeStar(view: View) =
        ObjectAnimator.ofFloat(view, View.ALPHA, 0f).apply {
            duration = 7000L
            repeatMode = ObjectAnimator.REVERSE
        }

    @SuppressLint("ObjectAnimatorBinding")
    private fun skyColor() =
        ObjectAnimator.ofArgb(
            mainBinding.imageStar.parent.parent,
            "backgroundColor",
            Color.BLACK,
            Color.RED
        ).apply {
            duration = 5000L
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = 1
        }

    private fun startAnimation(objectAnimation: ObjectAnimator, view: View) {
        objectAnimation.apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {
                    view.isEnabled = false
                }

                override fun onAnimationEnd(animation: Animator?) {
                    view.isEnabled = true
                }
            })
            start()
        }

    private fun showerStar() {
        val container = mainBinding.imageStar.parent as ViewGroup
        val containerWidth = container.width
        val containerHeight = container.height
        var starWidth = mainBinding.imageStar.width.toFloat()
        var starHeight = mainBinding.imageStar.height.toFloat()
        val newStar = AppCompatImageView(this)
        newStar.setImageResource(R.drawable.ic_star)
        newStar.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        container.addView(newStar)
        newStar.scaleX = Math.random().toFloat()
        newStar.scaleY = newStar.scaleX
        starWidth = newStar.scaleX
        starHeight = newStar.scaleY
        newStar.translationX = Math.random().toFloat() * containerWidth - starWidth / 2
        AnimatorSet().apply {
            playTogether(
                rotateStar(newStar, -720f, 0f).apply { repeatCount = ObjectAnimator.INFINITE },
                translateStar(
                    newStar,
                    "translationY",
                    0f,
                    100f,
                    Math.random().toFloat() * containerHeight + starHeight / 2
                ).apply {
                    repeatCount = ObjectAnimator.INFINITE
                },
                translateStar(
                    newStar,
                    "translationX",
                    Math.random().toFloat() * containerWidth /2,
                    Math.random().toFloat() * containerWidth * 2 + starHeight / 2
                ).apply {
                    repeatCount = ObjectAnimator.INFINITE
                },
                scaleStar(newStar, 1.2f, 0.5f, 1.3f, 0.1f).apply {
                    repeatCount = ObjectAnimator.INFINITE
                },
                fadeStar(newStar).apply {
                    repeatCount = ObjectAnimator.INFINITE
                },
                ObjectAnimator.ofArgb(
                    newStar,
                    "colorFilter",
                    Color.YELLOW,
                    Color.BLUE,
                    Color.GREEN,
                    Color.RED,
                    Color.GRAY,
                    Color.BLACK,
                    Color.CYAN
                ).apply {
                    duration = 2000
                    repeatCount = ObjectAnimator.INFINITE
                    repeatMode = ObjectAnimator.REVERSE
                }
            )
            start()
        }
    }
}