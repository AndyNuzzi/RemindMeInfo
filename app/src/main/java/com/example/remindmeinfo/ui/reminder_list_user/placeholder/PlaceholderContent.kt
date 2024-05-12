package com.example.remindmeinfo.ui.reminder_list_user.placeholder

import com.example.remindmeinfo.ui.reminder_admin.placeholder.PlaceholderContent
import com.example.remindmeinfo.ui.reminder_list_user.UserReminders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

object PlaceholderContent {

    val ITEMS: MutableList<UserReminders> = ArrayList()

    val dbReference = FirebaseFirestore.getInstance()

    val currentUser = FirebaseAuth.getInstance().currentUser


    fun createPlaceholderItem(onDataLoaded: () -> Unit) {

        //email user
        var email = PlaceholderContent.currentUser?.email.toString()

        dbReference.collection("users").document(email)
            .get()
            .addOnSuccessListener { document ->
                if (document != null){
                    val doc = document.getString("user_admin")

                    if (doc != null) {
                        dbReference.collection("reminders").document(doc).collection("remind")
                            .get()
                            .addOnSuccessListener { result ->
                                ITEMS.clear()
                                for (docs in result) {
                                    val rem_title = docs.getString("title") ?: "Sin título"
                                    val rem_id = docs.id
                                    val rem_color = docs.getString("color") ?: "#FFFFFF"
                                    val rem_fecha = docs.getString("date")

                                    if (rem_fecha == null){
                                        val rem = UserReminders(rem_title, rem_id, rem_color)
                                        ITEMS.add(rem)
                                    }
                                }
                                onDataLoaded()
                            }
                            .addOnFailureListener { exception ->
                            }
                    }
                }
            }
    }
}