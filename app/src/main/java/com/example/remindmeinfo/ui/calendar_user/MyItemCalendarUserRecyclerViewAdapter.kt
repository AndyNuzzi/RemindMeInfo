package com.example.remindmeinfo.ui.calendar_user

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.remindmeinfo.databinding.FragmentItemCalendarUserBinding


class MyItemCalendarUserRecyclerViewAdapter(
    private val values: List<CalendarReminders>,
    private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<MyItemCalendarUserRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemCalendarUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.title

        try {

            val color = Color.parseColor(item.color)
            holder.itemView.setBackgroundColor(color)
        } catch (e: IllegalArgumentException) {
            Log.e("MyItemRecyclerViewAdapter", "Formato de color no válido: $item.color", e)

            holder.itemView.setBackgroundColor(Color.WHITE)
        }

        holder.itemView.setOnClickListener {
            onItemClicked(item.id)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemCalendarUserBinding): RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + contentView.text
        }
    }

}