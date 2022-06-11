package com.capstone.medsapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.capstone.medsapp.ui.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        actionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, 3000)
    }
}