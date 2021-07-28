package com.taetae98.wildriftdictionary.activity

import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.databinding.ActivityMainBinding
import com.taetae98.wildriftdictionary.databinding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.newsFragment, R.id.championFragment, R.id.itemFragment, R.id.runeFragment
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateSupportActionBar()
        onCreateBottomNavigationView()
    }

    private fun onCreateSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun onCreateBottomNavigationView() {
        with(binding.bottomNavigationView) {
            setupWithNavController(navController)
        }
    }
}