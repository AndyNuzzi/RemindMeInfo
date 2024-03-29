package com.example.remindmeinfo.ui.medical_info_user

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentItemMedicalPdfBinding
import com.example.remindmeinfo.ui.medical_info_user.placeholder.PlaceholderContent
import com.example.remindmeinfo.ui.medical_info_user.placeholder.PlaceholderContent.PdfItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemMedicalPDFRecyclerViewAdapter(
    private val values: List<PdfItem>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MyItemMedicalPDFRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemMedicalPdfBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.date.text = item.date
        holder.depart.text = item.depart

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemMedicalPdfBinding) : RecyclerView.ViewHolder(binding.root) {
        val date: TextView = itemView.findViewById(R.id.date)
        val depart: TextView = itemView.findViewById(R.id.depart)

        override fun toString(): String {
            return super.toString() + date.text + depart.text
        }
    }

    interface OnItemClickListener {
        fun onItemClick(pdfItem: PdfItem)
    }

}