package com.naze.imageslidetest

import android.annotation.SuppressLint
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.internal.DescendantOffsetUtils
import com.naze.imageslidetest.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var screenHeight = 0
    private var isExpanded = false

    private val images = mutableListOf<Int>(
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background
    )

    private val texts = mutableListOf<String>(
        "Foreground",
        "Background"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initViewPager()
    }

    private fun initViewPager() {
        val imageAdapter = ImageSliderAdapter(this@MainActivity, images)

        binding.viewPager.apply {
            adapter = imageAdapter
        }

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.viewPager.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        val nextItem = Int.MAX_VALUE / 2 - ceil(images.size.toDouble() / 2 ).toInt() //무한 스크롤링
        binding.viewPager.setCurrentItem(nextItem, false)
        binding.tvViewCount.text = "${nextItem%images.size+1} / ${images.size}"
        binding.tvInfo.text = texts[nextItem%images.size]


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvViewCount.text = "${position%images.size+1} / ${images.size}"
                binding.tvInfo.text = texts[position%images.size]
            }
        })



    }


}