package com.sample.twowaybindingsample.ui.bottomnavigation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sample.twowaybindingsample.EventObserver
import com.sample.twowaybindingsample.R
import com.sample.twowaybindingsample.databinding.ActivityBottomNavigationBinding
import com.sample.twowaybindingsample.ui.bottomnavigation.dashboard.DashboardFragment
import com.sample.twowaybindingsample.ui.bottomnavigation.home.HomeFragment
import com.sample.twowaybindingsample.ui.bottomnavigation.notifications.NotificationsFragment

class BottomNavigationActivity : AppCompatActivity() {

    private val viewModel: BottomNavigationViewModel by viewModels()

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_navigation)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_main, HomeFragment())
                .commit()
        }

        // event observe
        viewModel.moveHomeEvent.observe(this, EventObserver {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, HomeFragment())
                .commit()
        })
        viewModel.moveDashboardEvent.observe(this, EventObserver {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, DashboardFragment())
                .commit()
        })
        viewModel.moveNotificationEvent.observe(this, EventObserver {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, NotificationsFragment())
                .commit()
        })
    }
}

interface OnAfterNavigationItemChangeListener {
    fun afterItemChanged(itemId: Int)
}

