package com.capstone.medsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.capstone.medsapp.databinding.ActivityResultBinding
import java.io.File
import android.graphics.Bitmap




class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var img: String = "image"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bitmap = intent.getParcelableExtra("image") as Bitmap?
        binding.previewImage.setImageBitmap(bitmap)
        Log.d("foto", bitmap.toString())

        val mBitmapFile = intent.getSerializableExtra("image") as File?
    }
}