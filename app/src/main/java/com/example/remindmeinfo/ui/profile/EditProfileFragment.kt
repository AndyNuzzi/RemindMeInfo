package com.example.remindmeinfo.ui.profile

import android.annotation.SuppressLint
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

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        var email = currentUser?.email.toString()
        var dbRegister = FirebaseFirestore.getInstance()

        val myButton: Button = view.findViewById(R.id.buttonSaveEdit)
        myButton.setOnClickListener {
            var name = view.findViewById<EditText>(R.id.textNameP).getText().toString()
            var surname = view.findViewById<EditText>(R.id.textSurnameP).getText().toString()
            var user_elder = view.findViewById<EditText>(R.id.textUserElder).getText().toString()

            if (name != ""){
                dbRegister.collection("users").document(email).update(
                    "name", name)
            }

            if (surname != ""){
                dbRegister.collection("users").document(email).update(
                    "surname", surname)
            }

            if (user_elder != ""){
                dbRegister.collection("users").document(email).update(
                    "user_elder", user_elder)
            }

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