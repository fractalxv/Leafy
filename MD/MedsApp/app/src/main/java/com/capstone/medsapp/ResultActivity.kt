package com.capstone.medsapp

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.medsapp.databinding.ActivityResultBinding
import java.io.File

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getFile = intent.getSerializableExtra("image") as File?
        val bitmap = BitmapFactory.decodeFile(getFile?.path)

        binding.previewImage.setImageBitmap(bitmap)
    }

    private fun showData(){
    }
}