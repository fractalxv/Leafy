package com.capstone.medsapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.capstone.medsapp.databinding.ActivityProcessBinding
import java.io.File

class ProcessActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProcessBinding
    private var getFile: File? = null
    private var max: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProcessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getFile = intent.getSerializableExtra("image") as File?
        max = intent.getSerializableExtra("array") as Int?

        actionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            val mIntent = Intent(this, ResultActivity::class.java)
            mIntent.putExtra("image", getFile)
            mIntent.putExtra("array", max)
            startActivity(mIntent)
            finish()
        }, 3000)
    }
}