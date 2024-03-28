package com.example.remindmeinfo.ui.reminder_list_user

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.remindmeinfo.databinding.FragmentItemUserReminderBinding


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyUserReminderRecyclerViewAdapter(
    private val values: List<UserReminders>,
    private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<MyUserReminderRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemUserReminderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.title

        holder.itemView.setOnClickListener {
            onItemClicked(item.id) // Llama al callback cuando se hace clic en el ítem
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemUserReminderBinding): RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + contentView.text
        }
    }

}