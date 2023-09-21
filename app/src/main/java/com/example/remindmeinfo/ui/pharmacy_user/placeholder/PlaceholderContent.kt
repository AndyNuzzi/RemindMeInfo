package com.example.remindmeinfo.ui.pharmacy_user.placeholder

import com.example.remindmeinfo.ui.pharmacy_user.Medications
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
    val ITEMS: MutableList<Medications> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, Medications> = HashMap()

    private val COUNT = 10

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createPlaceholderItem())
        }
    }

    private fun addItem(item: Medications) {
        ITEMS.add(item)
        ITEM_MAP.put(item.amount, item)
    }

    private fun createPlaceholderItem(): Medications {
        return Medications("1", "hola ", "2/2/2002")
    }


}