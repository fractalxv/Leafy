package com.capstone.medsapp.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.capstone.medsapp.databinding.ActivityResultBinding
import com.capstone.medsapp.db.DatabaseContract
import com.capstone.medsapp.db.NoteHelper
import com.capstone.medsapp.helper.MappingHelper
import kotlinx.coroutines.launch
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
        showData()
    }

    private fun showData() {
        val max = intent.getSerializableExtra("array") as Int?
        lifecycleScope.launch {
            val noteHelper = NoteHelper.getInstance(applicationContext)
            noteHelper.open()
            val cursor = noteHelper.queryById((max?.plus(1)).toString())
            MappingHelper.mapCursorToArrayList(cursor)

            cursor.moveToFirst()
            cursor.apply {
                val name = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.NAME))
                val description =
                    getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION))
                val khasiat = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.KHASIAT))

                binding.apply {
                    tvNama.text = name
                    tvDescription.text = description
                    tvDaun.text = name
                    tvIsikhasiat.text = khasiat
                }
            }

            cursor.close()
            noteHelper.close()
        }
    }
}