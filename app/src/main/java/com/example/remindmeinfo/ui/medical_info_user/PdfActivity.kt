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

        val url = intent.getStringExtra("pdfUrl")!!

        cargarInfoPDF(url)
    }

    private fun cargarInfoPDF(urlD: String) {
        // cargar el nombre del pdf de firebase firestore
        var url_pdf1 = ""
        var url_pdf2 = ""

        // Cargar y mostrar el PDF de
        val refDb = FirebaseFirestore.getInstance().collection("medical_info_pdfs")
        refDb.get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    url_pdf1 = doc.get("pdf1") as String
                    url_pdf2 = doc.get("pdf2") as String
                }
                if (url_pdf1 == urlD) {
                    val ref = FirebaseStorage.getInstance().getReference("medical_info")
                    ref.child(url_pdf1 as String)
                        .getBytes(Constants.Maximo_Bytes_PDF)
                        .addOnSuccessListener { bytes ->
                            // cargo el pdf
                            binding.VisualizarPDF.fromBytes(bytes)
                                .swipeHorizontal(false)
                                .load()
                        }
                } else if (url_pdf2 == urlD) {
                    val ref = FirebaseStorage.getInstance().getReference("medical_info")
                    ref.child(url_pdf2 as String)
                        .getBytes(Constants.Maximo_Bytes_PDF)
                        .addOnSuccessListener { bytes ->
                            // cargo el pdf
                            binding.VisualizarPDF.fromBytes(bytes)
                                .swipeHorizontal(false)
                                .load()
                        }

                }
            }
    }
}