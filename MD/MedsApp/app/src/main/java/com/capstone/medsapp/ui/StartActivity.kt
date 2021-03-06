package com.capstone.medsapp.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.capstone.medsapp.createCustomTempFile
import com.capstone.medsapp.databinding.ActivityStartBinding
import com.capstone.medsapp.ml.Leafy
import com.capstone.medsapp.uriToFile
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private var currentPhotoPath: String? = null
    private var getFile: File? = null
    private var selectedImg: Uri? = null
    private var max: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        setupAction()
    }

    private fun setupAction() {
        binding.btnCamera.setOnClickListener { startTakePhoto() }
        binding.btnGallery.setOnClickListener { startGallery() }
    }

    private fun submitPhoto() {
        if (currentPhotoPath != null) {
            val myFile = File(currentPhotoPath!!)
            getFile = myFile
            processML()
            val intent = Intent(this@StartActivity, ProcessActivity::class.java)
            intent.putExtra("image", getFile)
            intent.putExtra("array", max)
            startActivity(intent)
            currentPhotoPath = null
        } else if (selectedImg != null) {
            val intent = Intent(Intent.ACTION_VIEW)
            processML()
            intent.setClass(this@StartActivity, ProcessActivity::class.java)
            intent.putExtra("image", getFile)
            intent.putExtra("array", max)
            startActivity(intent)
            selectedImg = null
        }
    }

    private fun processML() {
        val bitmap = BitmapFactory.decodeFile(getFile?.path)
        val resized: Bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, false)
        val model = Leafy.newInstance(this)

        val fileName = "label.txt"
        val inputString = application?.assets?.open(fileName)?.bufferedReader().use {
            it?.readText()
        }
        val townList = inputString?.split("\n")

        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)

        val tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(resized)
        val byteBuffer = tensorImage.buffer
        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        if (townList != null) {
            Log.d(
                "Nama", "${townList[0]} : ${outputFeature0.floatArray[0]}\n" +
                        "${townList[1]} : ${outputFeature0.floatArray[1]}\n" +
                        "${townList[2]} : ${outputFeature0.floatArray[2]}\n" +
                        "${townList[3]} : ${outputFeature0.floatArray[3]}\n" +
                        "${townList[4]} : ${outputFeature0.floatArray[4]}\n" +
                        "${townList[5]} : ${outputFeature0.floatArray[5]}\n" +
                        "${townList[6]} : ${outputFeature0.floatArray[6]}\n"
            )
        }

        max = getMax(outputFeature0.floatArray)

        model.close()
    }

    private fun getMax(arr: FloatArray): Int {
        var idx = 0
        var min = 0.0f

        for (i in 0..6) {
            if (arr[i] > min) {
                idx = i
                min = arr[i]
            }
        }
        return idx
    }

    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)

        createCustomTempFile(application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this@StartActivity,
                "com.capstone.medsapp",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath!!)
            getFile = myFile

            submitPhoto()
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            selectedImg = result.data?.data as Uri

            val myFile = uriToFile(selectedImg!!, this@StartActivity)

            getFile = myFile

            submitPhoto()
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Permission not granted.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}