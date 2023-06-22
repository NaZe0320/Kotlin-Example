package com.naze.coordinatorlayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.naze.coordinatorlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val array = arrayListOf("Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum",
        "Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum",
        "Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum",
        "Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum",
        "Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum",)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setFloatActionButton()
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let { ab ->
            ab.setHomeAsUpIndicator(R.drawable.ic_menu)
            ab.setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbar.title = "ToolBar"
        binding.toolbar.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }
        binding.rvToDoList.layoutManager = LinearLayoutManager(this)
        binding.rvToDoList.adapter = MenuAdapter(array)
    }

    private fun setFloatActionButton() {
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "SnackBar", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        }
    }
}