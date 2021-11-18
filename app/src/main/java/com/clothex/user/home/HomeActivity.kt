package com.clothex.user.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.clothex.user.R
import com.clothex.user.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) =
            context.startActivity(Intent(context, HomeActivity::class.java))
    }

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navView.apply {
            itemIconTintList = null
            setupWithNavController(findNavController(R.id.nav_host_fragment_activity_main))
        }
    }
}