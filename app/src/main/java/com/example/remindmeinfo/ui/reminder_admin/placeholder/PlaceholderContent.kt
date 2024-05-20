package com.example.remindmeinfo.ui.reminder_admin.placeholder

import com.example.remindmeinfo.ui.reminder_admin.AdminReminders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

object PlaceholderContent {

    val ITEMS: MutableList<AdminReminders> = ArrayList()

    val dbReference = FirebaseFirestore.getInstance()

    val currentUser = FirebaseAuth.getInstance().currentUser


    fun createPlaceholderItem(onDataLoaded: () -> Unit){
        var email = currentUser?.email.toString()

        dbReference.collection("reminders").document(email).collection("remind")
            .get()
            .addOnSuccessListener { result ->
                ITEMS.clear()
                for (doc in result) {
                    val rem_title = doc.getString("title") ?: "Sin título"
                    val rem_id = doc.id
                    val rem_color = doc.getString("color") ?: "#FFFFFF"
                    val rem = AdminReminders(rem_title, rem_id, rem_color)
                    ITEMS.add(rem)
                }
                onDataLoaded()
            }
            .addOnFailureListener { exception ->
            }
    }

}