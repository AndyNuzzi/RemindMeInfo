package com.example.remindmeinfo.ui.medical_info_user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.remindmeinfo.R
import com.example.remindmeinfo.ui.medical_info_user.placeholder.PlaceholderContent
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * A fragment representing a list of Items.
 */
class ItemMedicalPDFFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list_medical_pdf, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                val adapter = MyItemMedicalPDFRecyclerViewAdapter(
                    PlaceholderContent.ITEMS,
                    object : MyItemMedicalPDFRecyclerViewAdapter.OnItemClickListener {
                        override fun onItemClick(pdfItem: PlaceholderContent.PdfItem) {
                            // Lógica para manejar el clic en el elemento
                            val url = pdfItem.name

                            val intent = Intent(requireContext(), PdfActivity:: class.java)
                            intent.putExtra("pdfUrl", url)
                            startActivity(intent)
                        }
                    }
                )

                this.adapter = adapter

            }
        }
        return view
    }

}