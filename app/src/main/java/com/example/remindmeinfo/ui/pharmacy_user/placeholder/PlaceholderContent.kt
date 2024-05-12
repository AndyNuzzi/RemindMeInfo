package com.example.remindmeinfo.ui.pharmacy_user.placeholder

import com.example.remindmeinfo.ui.pharmacy_user.Medications
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

object PlaceholderContent {

    val ITEMS: MutableList<Medications> = ArrayList()

    val dbReference = FirebaseFirestore.getInstance()

    fun createPlaceholderItem(onDataLoaded: () -> Unit){
        var med_amount = ""
        var med_date = ""
        var med_name = ""

        dbReference.collection("pharmacy_medications")
            .get()
            .addOnSuccessListener{ result ->
                ITEMS.clear()
                for (doc in result){
                    med_amount = doc.get("amount") as String
                    med_date = doc.get("date") as String
                    med_name = doc.get("name") as String

                    val med = Medications(med_amount, med_date, med_name)

                    if (med != null) {
                        ITEMS.add(med)
                    }
                    onDataLoaded()
                }
            }
    }

}
