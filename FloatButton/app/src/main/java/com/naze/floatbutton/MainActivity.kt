package com.naze.floatbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.naze.floatbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

    private fun setFab() {
        var clickedFab = false
        binding.fabMain.setOnClickListener {
            clickedFab = !clickedFab
            clickFab(clickedFab)
        }
    }

    private fun clickFab(clickedFab: Boolean) {
        val rotateOpen = AnimationUtils.loadAnimation(applicationContext,R.anim.rotate_open_anim)
        val rotateClose = AnimationUtils.loadAnimation(applicationContext,R.anim.rotate_close_anim)
        val toBottom = AnimationUtils.loadAnimation(applicationContext,R.anim.to_bottom_anim)
        val fromBottom = AnimationUtils.loadAnimation(applicationContext,R.anim.from_bottom_anim)

        if (clickedFab) {
            binding.fab1.visibility = View.VISIBLE
            binding.fab2.visibility = View.VISIBLE
            binding.fab3.visibility = View.VISIBLE

            binding.fabMain.startAnimation(rotateOpen)
            binding.fab1.startAnimation(fromBottom)
            binding.fab2.startAnimation(fromBottom)
            binding.fab3.startAnimation(fromBottom)
        } else {
            binding.fab1.visibility = View.GONE
            binding.fab2.visibility = View.GONE
            binding.fab3.visibility = View.GONE

            binding.fabMain.startAnimation(rotateClose)
            binding.fab1.startAnimation(toBottom)
            binding.fab2.startAnimation(toBottom)
            binding.fab3.startAnimation(toBottom)
        }
    }
}