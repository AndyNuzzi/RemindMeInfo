package com.example.remindmeinfo.ui.reminder_admin.placeholder


import com.example.remindmeinfo.ui.reminder_admin.AdminReminders
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

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
    val ITEMS: MutableList<AdminReminders> = ArrayList()

    val dbReference = FirebaseFirestore.getInstance()


    fun createPlaceholderItem(onDataLoaded: () -> Unit){


        dbReference.collection("reminders")
            .get()
            .addOnSuccessListener { result ->
                ITEMS.clear()
                for (doc in result) {
                    val rem_title = doc.getString("title") ?: "Sin título"
                    val rem = AdminReminders(rem_title)
                    ITEMS.add(rem)
                }
                onDataLoaded()
            }
            .addOnFailureListener { exception ->
                // Aquí manejarías el error, como mostrar un mensaje al usuario.
            }
    }

}