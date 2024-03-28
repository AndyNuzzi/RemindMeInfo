package com.example.remindmeinfo.ui.reminder_admin

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.TextView
import com.example.remindmeinfo.databinding.FragmentItemReminderAdminBinding


class MyItemReminderAdminRecyclerViewAdapter(
    private val values: List<AdminReminders>,
    private val onItemClicked: (String) -> Unit
)
    : RecyclerView.Adapter<MyItemReminderAdminRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    return ViewHolder(
        FragmentItemReminderAdminBinding.inflate(
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

    inner class ViewHolder(binding: FragmentItemReminderAdminBinding) : RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + contentView.text
        }
    }

}