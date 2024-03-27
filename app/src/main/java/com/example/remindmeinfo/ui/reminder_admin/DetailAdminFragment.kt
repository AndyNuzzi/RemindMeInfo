package com.example.remindmeinfo.ui.reminder_admin

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentDetailAdminBinding
import com.example.remindmeinfo.databinding.FragmentHelpBinding
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text


class DetailAdminFragment : Fragment() {

    private var _binding: FragmentDetailAdminBinding? = null

    private lateinit var message1: TextView

    private var backgroundColor: Int? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento

        val db = FirebaseFirestore.getInstance()


        db.collection("reminders")
            .document()
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val colorString = document.getString("color")
                    if (colorString != null) {
                        try{
                            backgroundColor = Color.parseColor(colorString)
                        } catch(e: IllegalArgumentException){ }
                    }
                }
            }

        val view = inflater.inflate(R.layout.fragment_detail_admin, container, false)

        message1 = view.findViewById(R.id.detailTextView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Una vez la vista ha sido creada y si el color de fondo se ha cargado, aplicar el color.
        backgroundColor?.let { color ->
            view.findViewById<View>(R.id.detailTextView)?.setBackgroundColor(color)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}