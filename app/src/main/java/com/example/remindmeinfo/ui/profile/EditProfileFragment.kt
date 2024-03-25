package com.example.remindmeinfo.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentEditProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null

    val currentUser = FirebaseAuth.getInstance().currentUser


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        var email = currentUser?.email.toString()
        var dbRegister = FirebaseFirestore.getInstance()

        var name = view.findViewById<EditText>(R.id.textNameP).getText().toString()
        var surname = view.findViewById<EditText>(R.id.textSurnameP).getText().toString()
        var age = view.findViewById<EditText>(R.id.textAgeP).getText().toString()
        var genre = view.findViewById<EditText>(R.id.textGenreP).getText().toString()
        var info = view.findViewById<EditText>(R.id.textMedicalInfoP).getText().toString()
        var familyName = view.findViewById<EditText>(R.id.familyNameP).getText().toString()


        dbRegister.collection("users").document(email).update(
            "name", name)
        dbRegister.collection("users").document(email).update(
            "surname", surname)
        dbRegister.collection("users").document(email).update(
            "age", age)
        dbRegister.collection("users").document(email).update(
            "genre", genre)
        dbRegister.collection("users").document(email).update(
            "aditional_info", info)
        dbRegister.collection("users").document(email).update(
            "family", familyName)

        val myButton: Button = view.findViewById(R.id.buttonSaveEdit)
        myButton.setOnClickListener {
            // Aquí manejas el evento clic del botón
            saveEdit()
        }

        return view
    }

    private fun saveEdit() {
        val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, ProfileAdminFragment())
        fragmentTransaction.commit()
    }

}