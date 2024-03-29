package com.example.remindmeinfo.ui.medical_info_user.placeholder

import com.example.remindmeinfo.ui.pharmacy_user.Medications
import com.example.remindmeinfo.ui.pharmacy_user.placeholder.PlaceholderContent
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
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

    /**
     * A placeholder item representing a piece of content.
     */
    data class PdfItem(val date: String, val depart: String, val url: String)
}
