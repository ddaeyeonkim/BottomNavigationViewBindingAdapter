package com.sample.twowaybindingsample.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sample.twowaybindingsample.R
import com.sample.twowaybindingsample.databinding.ActivityMainBinding
import com.sample.twowaybindingsample.ui.bottomnavigation.BottomNavigationActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        binding.vm = viewModel
        binding.lifecycleOwner = this

        binding.btnBottomNavigation.setOnClickListener {
            startActivity(Intent(this, BottomNavigationActivity::class.java))
        }
    }
}