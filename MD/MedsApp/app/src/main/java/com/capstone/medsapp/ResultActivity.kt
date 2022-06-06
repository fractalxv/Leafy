package com.capstone.medsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.capstone.medsapp.databinding.ActivityResultBinding
import java.io.File
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri


class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mBitmapFile = intent.getSerializableExtra("image") as File?
        val bitmap = BitmapFactory.decodeFile(mBitmapFile?.path)
        /*val selectedImg: Uri = result.data?.data as Uri*/

        if (bitmap != null) {
            binding.previewImage.setImageBitmap(bitmap)
        } /*else {
            binding.previewImage.setImageURI(selectedImg)
        }*/
    }
}