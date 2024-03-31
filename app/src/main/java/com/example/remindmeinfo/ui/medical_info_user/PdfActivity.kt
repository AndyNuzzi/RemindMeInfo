package com.example.remindmeinfo.ui.medical_info_user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.remindmeinfo.databinding.ActivityPdfBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class PdfActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPdfBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPdfBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val url = intent.getStringExtra("pdfUrl")!!

        cargarInfoPDF(url)
    }

    private fun cargarInfoPDF(urlD: String) {
        // Cargar y mostrar el PDF
        val ref = FirebaseStorage.getInstance().getReference("medical_info")
        ref.child(urlD)
            .getBytes(Constants.Maximo_Bytes_PDF)
            .addOnSuccessListener { bytes ->
                // cargo el pdf
                binding.VisualizarPDF.fromBytes(bytes)
                    .swipeHorizontal(false)
                    .load()
            }
    }
}

