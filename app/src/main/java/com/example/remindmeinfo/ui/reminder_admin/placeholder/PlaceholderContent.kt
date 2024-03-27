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

    init {
        //for (i in 1.. ITEMS.lastIndex){
        createPlaceholderItem()
        // }

    }

    private fun createPlaceholderItem(): List<AdminReminders>{
        var rem_title = ""

        //val db =
        dbReference.collection("reminders")
            .get()
            .addOnSuccessListener{ result ->
                for (doc in result){
                    rem_title  = doc.get("title") as String

                    val rem = AdminReminders(rem_title)

                    if (rem != null) {
                        ITEMS.add(rem)
                    }

                }

            }
        return ITEMS
    }

}