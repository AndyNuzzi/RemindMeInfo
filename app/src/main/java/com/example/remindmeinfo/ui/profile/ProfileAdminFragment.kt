package com.example.remindmeinfo.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.remindmeinfo.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileAdminFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    val usuario = FirebaseAuth.getInstance().currentUser
    val uid = usuario?.email

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dbReference = FirebaseFirestore.getInstance()

        lateinit var textViewName: TextView
        lateinit var textViewSurname: TextView
        lateinit var textViewGenre: TextView
        lateinit var textViewAge: TextView
        lateinit var textViewAddInfo: TextView


        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        textViewName = binding.textNameAdmin
        textViewSurname = binding.textSurnameAdmin
        textViewGenre = binding.textGenreAdmin
        textViewAge = binding.textAgeAdmin
        textViewAddInfo = binding.textInfoAdmin

        if (uid != null) {
            dbReference.collection("users").document(uid)
                .get()
                .addOnSuccessListener { documento ->
                    if (documento != null) {
                        val nombre = documento.getString("name")
                        textViewName.text = nombre

                        val apellido = documento.getString("surname")
                        textViewSurname.text = apellido

                        val genero = documento.getString("genre")
                        textViewGenre.text = genero

                        val edad = documento.getString("age")
                        textViewAge.text = edad

                        val infoAdd = documento.getString("aditional_info")
                        textViewAddInfo.text = infoAdd

                    } else {
                        Log.d("Fragment", "No such document")
                    }
                }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}