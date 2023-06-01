package com.naze.imageslidetest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.databinding.DataBindingUtil
import com.naze.imageslidetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var screenHeight = 0
    private var isExpanded = false

    private val images = listOf(
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background
    )
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewPager.adapter = ImageSliderAdapter(images = images)

        binding.viewPager.setPageTransformer { page, position ->
            val offset = resources.getDimensionPixelOffset(R.dimen.viewpager_next_item_offset)
            page.translationX = -position * offset
        }


    }
}