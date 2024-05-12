package com.example.remindmeinfo.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentProfileUserFromBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileUserFromFragment : Fragment() {
    private var _binding: FragmentProfileUserFromBinding? = null

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
        lateinit var textViewEmail: TextView
        lateinit var textViewCipa: TextView


        _binding = FragmentProfileUserFromBinding.inflate(inflater, container, false)
        val root: View = binding.root

        textViewName = binding.textName
        textViewSurname = binding.textSurname
        textViewGenre = binding.textGenre
        textViewAge = binding.textAge
        textViewAddInfo = binding.textInfo
        textViewEmail = binding.textEmail
        textViewCipa = binding.textCipa

        if (uid != null) {
            dbReference.collection("users").document(uid)
                .get()
                .addOnSuccessListener { documento ->
                    if (documento != null) {
                        val user_elder = documento.getString("user_elder")
                        if (user_elder != null) {
                            dbReference.collection("users").document(user_elder)
                                .get()
                                .addOnSuccessListener { doc ->
                                    val nombre = doc.getString("name")
                                    textViewName.text = nombre

                                    val apellido = doc.getString("surname")
                                    textViewSurname.text = apellido

                                    val genero = doc.getString("genre")
                                    textViewGenre.text = genero

                                    val edad = doc.getString("age")
                                    textViewAge.text = edad

                                    val cipa = doc.getString("cipa_number")
                                    textViewCipa.text = cipa

                                    val email = doc.getString("user")
                                    textViewEmail.text = email

                                    val infoAdd = doc.getString("aditional_info")
                                    textViewAddInfo.text = infoAdd
                                }
                        }
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myButton: Button = view.findViewById(R.id.buttonEditProfileUser)
        myButton.setOnClickListener {
            // Manejo el evento clic del botón
            editProfile()
        }
    }

    fun editProfile(){
        val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, EditProfileUserAdminFragment())
        fragmentTransaction.commit()
    }
}