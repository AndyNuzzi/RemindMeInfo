package com.example.remindmeinfo.ui.medical_info_user.placeholder

import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

object PlaceholderContent {

    val ITEMS: MutableList<PdfItem> = ArrayList()

    private val dbReference = FirebaseFirestore.getInstance()

    fun createPlaceholderItem(onDataLoaded: () -> Unit) {
        var depart = ""
        var date = ""
        var url = ""

        dbReference.collection("medical_info_pdfs")
            .get()
            .addOnSuccessListener { result ->
                ITEMS.clear()
                for (doc in result) {
                    date = doc.get("date") as String
                    depart = doc.get("department") as String
                    url = doc.get("url") as String

                    val pdf_Item = PdfItem(date, depart, url)

                    // Agregar a la lista de PDFs
                    if(pdf_Item != null){
                        ITEMS.add(pdf_Item)
                    }
                    onDataLoaded()
                }
            }
    }

    data class PdfItem(val date: String, val depart: String, val url: String)
}
