package com.capstone.medsapp.ui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.medsapp.databinding.ActivityMainBinding
import com.capstone.medsapp.db.DatabaseContract
import com.capstone.medsapp.db.NoteHelper
import com.capstone.medsapp.entity.Note

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var note: Note? = null
    private lateinit var noteHelper: NoteHelper

    companion object {
        const val EXTRA_NOTE = "extra_note"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteHelper = NoteHelper(this);
        noteHelper = NoteHelper.getInstance(applicationContext)
        noteHelper.open()

        note = intent.getParcelableExtra(EXTRA_NOTE)
        inputData()
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

    private fun inputData(){
        val values1 = ContentValues()
        values1.put(DatabaseContract.NoteColumns.NAME, "Daun Jambu Biji")
        values1.put(DatabaseContract.NoteColumns.DESCRIPTION, "Ya, daun jambu biji merupakan salah satu ramuan herbal tradisional masyarakat yang sangat populer, terutama untuk mengatasi diare dan perut kembung. Tanaman ini juga banyak ditemui di seluruh wilayah Indonesia. Daun jambu biji biasanya dikonsumsi dengan cara direbus bersama air untuk mendapatkan ekstraknya.")
        values1.put(DatabaseContract.NoteColumns.KHASIAT, "Menurunkan berat badan, mengobati diare dan disentri, mengobati diabetes, turunkan kolesterol, mempelancar saluran pencernaan, mengobati bronkitis, mengobati sakit gigi, gusi, dan tenggorokan, mengobati demam berdarah, mengobati luka dan infeksi.")
        noteHelper.insert(values1)

        val values2 = ContentValues()
        values2.put(DatabaseContract.NoteColumns.NAME, "Daun Kari")
        values2.put(DatabaseContract.NoteColumns.DESCRIPTION, "Daun Kari (Salam koja) atau temurui adalah tumbuhan yang daunnya dipakai sebagai bumbu kari. Daun ini dipakai sebagai bumbu di Aceh. Bentuk daun ini mirip dengan daun salam dengan ukuran yang lebih kecil dan bau yang lebih tajam.")
        values2.put(DatabaseContract.NoteColumns.KHASIAT, "Menurunkan berat badan, mengobati diabetes, menjaga kesehatan usus, dan mengelola kolesterol. ")
        noteHelper.insert(values2)

        val values3 = ContentValues()
        values3.put(DatabaseContract.NoteColumns.NAME, "Daun Kunyit")
        values3.put(DatabaseContract.NoteColumns.DESCRIPTION, "Kunyit adalah termasuk salah satu tanaman rempah-rempah dan obat asli dari wilayah Asia Tenggara. Tanaman ini kemudian mengalami penyebaran ke daerah Malaysia, Indonesia, Australia bahkan Afrika. Kunyit umumnya dikonsumsi sebagai pelengkap bumbu masakan dan obat.")
        values3.put(DatabaseContract.NoteColumns.KHASIAT, "Meningkatkan kesehatan pencernaan, bersifat sebagai antiradang, menyembuhkan luka, mencegah diabetes, melembutkan dan mencerahkan kulit, anti inflamasi.")
        noteHelper.insert(values3)

        val values4 = ContentValues()
        values4.put(DatabaseContract.NoteColumns.NAME, "Daun Pepaya")
        values4.put(DatabaseContract.NoteColumns.DESCRIPTION, "Daun pepaya merupakan daun yang berasal dari tanaman pepaya yang umum ditemukan di Indonesia. Tanaman tersebut memiliki nama latin carica papaya dan dapat tumbuh mulai dari 5 meter hingga 10 meter. Tanaman pepaya juga dapat ditemukan di daerah tropis dan subtropis.")
        values4.put(DatabaseContract.NoteColumns.KHASIAT, "Mengobati gejala demam berdarah, menyeimbangkan gula darah, melancarkan sistem pencernaan, meredakan peradangan sendi, membantu pertumbuhan rambut, menghilangkan bekas luka, memiliki sifat antikanker.")
        noteHelper.insert(values4)

        val values5 = ContentValues()
        values5.put(DatabaseContract.NoteColumns.NAME, "Daun Sirih")
        values5.put(DatabaseContract.NoteColumns.DESCRIPTION, "Sirih adalah tanaman asli Indonesia yang tumbuh merambat atau bersandar pada batang pohon lain. Sirih dikenal dalam masing-masing bahasa dengan nama yang khas, yaitu: suro, sireh, bido, base, dan amo. Sebagai budaya daun dan buahnya biasa dikunyah bersama gambir, pinang, tembakau dan kapur.")
        values5.put(DatabaseContract.NoteColumns.KHASIAT, "Memperlancar saluran pencernaan, menjaga kesehatan mulut dan gigi, sebagai antibakteri, membantu penyembuhan luka, mengobati mimisan, mengobati radang prostat, mencegah malaria, membantu mengurangi depresi, mengobati batuk.")
        noteHelper.insert(values5)

        val values6 = ContentValues()
        values6.put(DatabaseContract.NoteColumns.NAME, "Daun Sirsak")
        values6.put(DatabaseContract.NoteColumns.DESCRIPTION, "Daun sirsak adalah daun yang berasal dari tanaman sirsak yang umum ditemukan di Indonesia. tumbuhan ini berasal dari Karibia, Amerika Tengah dan Amerika Selatan. Di berbagai daerah Indonesia dikenal sebagai nangka sebrang, nangka landa , nangka walanda, nangka buris, nangkèlan, nangka ènglan, srikaya jawa, boh lôna, durio ulondro , durian betawi ,durian belanda, serta jambu landa.")
        values6.put(DatabaseContract.NoteColumns.KHASIAT, "Mengobati kanker, menurunkan kolesterol, mengobati sariawan, mengobati asam urat, mengatasi masalah jerawat, dan menurunkan kadar gula darah.")
        noteHelper.insert(values6)

        val values7 = ContentValues()
        values7.put(DatabaseContract.NoteColumns.NAME, "Lidah Buaya")
        values7.put(DatabaseContract.NoteColumns.DESCRIPTION, "Lidah buaya  (Aloe vera) adalah spesies tumbuhan dengan daun berdaging tebal dari genus Aloe. Tumbuhan ini bersifat menahun, berasal dari Jazirah Arab, dan tanaman liarnya telah menyebar ke kawasan beriklim tropis, semi-tropis, dan kering di berbagai belahan dunia. Tanaman lidah buaya banyak dibudidayakan untuk pertanian, pengobatan, dan tanaman hias, dan dapat juga ditanam di dalam pot.")
        values7.put(DatabaseContract.NoteColumns.KHASIAT, "Mengatasi kulit kering, menghilangkan jerawat, mengatasi mata bengkak, mempercepat penyembuhan luka, meredakan gatal dan ruam kronis, menurunkan kadar gula darah, melancarkan buang air besar.")
        noteHelper.insert(values7)
    }
}