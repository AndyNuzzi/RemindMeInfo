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

    private val dbSReference = FirebaseStorage.getInstance().getReference()

    init {
        createPlaceholderItem()

    }

    private fun createPlaceholderItem(): List<PdfItem> {

        var ref = dbSReference.child("medical_info")

        ref.listAll().addOnSuccessListener{ result ->
                for (item in result.items){
                    val pdfName = item.name
                    val pdfUrl = item.downloadUrl.toString()

                    var pdf_Item = PdfItem(pdfName, pdfUrl)
                    // Agregar a la lista de PDFs
                    ITEMS.add(pdf_Item)
                }

            }
        return ITEMS
    }

    /**
     * A placeholder item representing a piece of content.
     */
    data class PdfItem(val name: String, val url: String) {
        override fun toString(): String = name
    }
}
