package com.naze.coordinatorlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.naze.coordinatorlayout.databinding.ActivityMainBinding
import com.naze.coordinatorlayout.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sub)

        val collapsingToolbar: CollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        collapsingToolbar.title = "CollapsingToolBar"
        collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.black))
        collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.black))
        collapsingToolbar.expandedTitleTextSize = resources.getDimension(R.dimen.collapsed_text_size)

        val imageView = binding.backdrop
        Glide.with(imageView)
            .load(R.drawable.common)
            .apply(RequestOptions.centerCropTransform())
            .into(imageView)
    }
}