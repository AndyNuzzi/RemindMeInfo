package com.example.remindmeinfo.ui.calendar_user

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentItemCalendarUserBinding

import com.example.remindmeinfo.ui.reminder_list_user.UserReminders

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
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
            // Parsea el color y establece el color de fondo del layout del ítem
            val color = Color.parseColor(item.color)
            holder.itemView.setBackgroundColor(color)
        } catch (e: IllegalArgumentException) {
            Log.e("MyItemRecyclerViewAdapter", "Formato de color no válido: $item.color", e)
            // Opcionalmente, puedes establecer un color predeterminado si el formato no es válido
            holder.itemView.setBackgroundColor(Color.WHITE)
        }

        holder.itemView.setOnClickListener {
            onItemClicked(item.id) // Llama al callback cuando se hace clic en el ítem
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