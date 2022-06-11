package com.capstone.medsapp.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.medsapp.databinding.ActivityResultBinding
import com.capstone.medsapp.db.DatabaseContract
import com.capstone.medsapp.db.NoteHelper
import java.io.File

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var noteHelper: NoteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getFile = intent.getSerializableExtra("image") as File?
        val bitmap = BitmapFactory.decodeFile(getFile?.path)

        noteHelper = NoteHelper(this);
        noteHelper = NoteHelper.getInstance(applicationContext)
        noteHelper.open()

        binding.previewImage.setImageBitmap(bitmap)
        showData()
    }

    private fun showData(){
        val max = intent.getSerializableExtra("array") as Int?
        binding.apply {
            tvNama.text = noteHelper.queryById(DatabaseContract.NoteColumns.NAME).toString()
            tvDescription.text = DatabaseContract.NoteColumns.DESCRIPTION
            tvDaun.text = DatabaseContract.NoteColumns.NAME
            tvIsikhasiat.text = DatabaseContract.NoteColumns.KHASIAT
        }
    }
}