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
import com.google.android.material.bottomsheet.BottomSheetBehavior
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

        val bottomBehavior = BottomSheetBehavior.from(binding.bottomSheet.root)

        bottomBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        bottomBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        //완전히 펼쳐진 상태
                        Log.d("BottomSheetBehavior Test","STATE_EXPANDED")
                        binding.bottomSheet.tvBottomTitle.text = "완전 펼쳐짐!"
                        binding.bottomSheet.iconTitle.text = "완전 펼쳐짐!"
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        //접혀있는 상태
                        Log.d("BottomSheetBehavior Test","STATE_COLLAPSED")
                        binding.bottomSheet.tvBottomTitle.text = "접힘!"
                        binding.bottomSheet.iconTitle.text = "접힘!"
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        //아래로 숨겨진 상태(안 보일 때)
                        Log.d("BottomSheetBehavior Test","STATE_SETTLING")
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        //절반 펼쳐진 상태
                        Log.d("BottomSheetBehavior Test","STATE_HALF_EXPANDED")
                        binding.bottomSheet.tvBottomTitle.text = "반만 펼쳐짐!"
                        binding.bottomSheet.iconTitle.text = "반만 펼쳐짐!"
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        //드래그 되고 있는 상태
                        Log.d("BottomSheetBehavior Test","STATE_DRAGGING")
                        binding.bottomSheet.tvBottomTitle.text = "드래그 중!"
                        binding.bottomSheet.iconTitle.text = "드래그 중!"
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        //드래그/스와이프 직후 고정된 상태
                        Log.d("BottomSheetBehavior Test","STATE_SETTLING")
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

        })
    }


}