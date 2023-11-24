package com.example.remindmeinfo.ui.medical_info_user

import android.app.Application
import android.widget.ProgressBar
import com.github.barteksc.pdfviewer.PDFView
import com.google.firebase.storage.FirebaseStorage

class Functions: Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object{

        fun loadPdfsURL(pdfURL: String, pdfView: PDFView){
            val ref = FirebaseStorage.getInstance().getReferenceFromUrl(pdfURL)
            ref.getBytes(Constants.Maximo_Bytes_PDF)
                .addOnSuccessListener { bytes ->
                    pdfView.fromBytes(bytes)
                        .pages(0)
                        .spacing(0)
                        .swipeHorizontal(false)
                        .enableSwipe(false)
                        .load()

                }
                .addOnFailureListener{ e ->

                }
        }
    }
}