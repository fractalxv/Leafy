package com.capstone.medsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.medsapp.databinding.ActivityStartBinding

import android.content.Intent

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction(){
        binding.btnCamera.setOnClickListener {
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivity(intent)
        }
    }
}