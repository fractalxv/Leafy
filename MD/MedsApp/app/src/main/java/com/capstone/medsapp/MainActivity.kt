package com.capstone.medsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.medsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.btnStart.setOnClickListener {
            val intent = Intent(this@MainActivity, StartActivity::class.java)
            startActivity(intent)
        }
        binding.btnLeafyteam.setOnClickListener {
            val intent = Intent(this@MainActivity, DeveloperActivity::class.java)
            startActivity(intent)
        }
    }
}