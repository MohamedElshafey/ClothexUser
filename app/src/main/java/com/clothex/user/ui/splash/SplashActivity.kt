package com.clothex.user.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.clothex.user.MainActivity
import com.clothex.user.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)

        findViewById<ImageView>(R.id.logoImg).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}