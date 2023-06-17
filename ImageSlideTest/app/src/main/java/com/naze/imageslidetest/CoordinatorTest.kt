package com.naze.imageslidetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.naze.imageslidetest.databinding.ActivityCoordinatorTestBinding
import com.naze.imageslidetest.databinding.ActivityMainBinding

class CoordinatorTest : AppCompatActivity() {

    private lateinit var binding: ActivityCoordinatorTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coordinator_test)


    }
}