package com.example.remindmeinfo.ui.reminder_list_user

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.remindmeinfo.databinding.FragmentDetailUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class DetailUserFragment : Fragment() {

    private var _binding: FragmentDetailUserBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Ya no necesitas declarar las variables 'message1', 'message2', 'message3' aquí
        // debido a que puedes acceder a ellas directamente desde el binding.

        val documentId = arguments?.getString("documentId")
        if (documentId == null) {
            Log.w("DetailViewFragment", "No se proporcionó 'documentId'.")
            return root // Retorna aquí si no hay ID de documento, ya que no podemos proceder sin él.
        }

        // email --> user receptor
        val currentUser = FirebaseAuth.getInstance().currentUser
        var email = currentUser?.email.toString()

        FirebaseFirestore.getInstance().collection("users").document(email)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val admin_user = document.getString("user_admin").toString()

                    val db = FirebaseFirestore.getInstance().collection("reminders")
                        .document(admin_user)
                        .collection("remind")
                        .document(documentId)

                    db.get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                // Extracción y aplicación del color
                                val color = document.getString("color")
                                    ?: "#FFFFFF" // Valor predeterminado si 'color' es null.
                                try {
                                    root.setBackgroundColor(Color.parseColor(color)) // Usa 'root' para cambiar el color de fondo.
                                } catch (e: IllegalArgumentException) {
                                    root.setBackgroundColor(Color.parseColor("#FFFFFF")) // Color predeterminado en caso de error.
                                }

                                // Configuración de los textos
                                val info = document.getString("subtitle")
                                val info_saltos = info?.replace(", ", ",\n ")

                                binding.detailTextViewUser.text = document.getString("title")
                                binding.detailSubtitleUser.text = info_saltos
                                binding.detailDateUser.text = document.getString("date") ?: ""
                            } else {
                                Log.w("DetailViewFragment", "El documento solicitado no existe.")
                            }
                        }
                        .addOnFailureListener { e ->
                            Log.w("FirestoreError", "Error al leer el documento", e)
                        }
                }
            }

        return root
    }
}