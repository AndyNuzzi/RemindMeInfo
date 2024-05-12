package com.example.remindmeinfo.ui.reminder_admin

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.remindmeinfo.databinding.FragmentDetailAdminBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class DetailAdminFragment : Fragment() {
    val currentUser = FirebaseAuth.getInstance().currentUser
    var email = currentUser?.email.toString()

    private var _binding: FragmentDetailAdminBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val documentId = arguments?.getString("documentId")
        if (documentId == null) {
            Log.w("DetailViewFragment", "No se proporcionó 'documentId'.")
            return root
        }


        val db = FirebaseFirestore.getInstance().collection("reminders")
            .document(email)
            .collection("remind")
            .document(documentId)

        db.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Extracción y aplicación del color
                    val color = document.getString("color") ?: "#FFFFFF" // Valor predeterminado si 'color' es null.
                    try {
                        root.setBackgroundColor(Color.parseColor(color)) // Usa 'root' para cambiar el color de fondo.
                    } catch (e: IllegalArgumentException) {
                        root.setBackgroundColor(Color.parseColor("#FFFFFF")) // Color predeterminado en caso de error.
                    }

                    // Configuración de los textos
                    val info = document.getString("subtitle")
                    val info_saltos = info?.replace(", ", "\n ")

                    binding.detailTextView.text = document.getString("title")
                    binding.detailSubtitle.text = info_saltos
                    binding.detailDate.text = document.getString("date") ?: ""
                } else {
                    Log.w("DetailViewFragment", "El documento solicitado no existe.")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FirestoreError", "Error al leer el documento", e)
            }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

