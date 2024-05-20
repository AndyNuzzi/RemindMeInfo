package com.example.remindmeinfo.ui.calendar_user.placeholder

import com.example.remindmeinfo.ui.calendar_user.CalendarReminders
import com.example.remindmeinfo.ui.reminder_admin.placeholder.PlaceholderContent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

object PlaceholderContent {


    val ITEMS: MutableList<CalendarReminders> = ArrayList()

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
                                    val rem_date = docs.getString("date")
                                    var dateToday = SimpleDateFormat("dd/MM/yyyy").format(Date())

                                    if (rem_date == dateToday){
                                        val rem_title = docs.getString("title") ?: "Sin título"
                                        val rem_id = docs.id
                                        val rem_color = docs.getString("color") ?: "#FFFFFF"
                                        val rem = CalendarReminders(rem_title, rem_id, rem_color)
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