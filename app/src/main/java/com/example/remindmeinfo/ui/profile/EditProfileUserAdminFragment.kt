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
import com.example.remindmeinfo.databinding.FragmentEditProfileUserAdminBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class EditProfileUserAdminFragment : Fragment() {

    private var _binding: FragmentEditProfileUserAdminBinding? = null

    val currentUser = FirebaseAuth.getInstance().currentUser


    private val binding get() = _binding!!

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_edit_profile_user_admin, container, false)

        var email = currentUser?.email.toString()
        var dbRegister = FirebaseFirestore.getInstance()

        val myButton: Button = view.findViewById(R.id.buttonSaveEdit)
        myButton.setOnClickListener {
            var name = view.findViewById<EditText>(R.id.textNameProf).getText().toString()
            var surname = view.findViewById<EditText>(R.id.textSurnameProf).getText().toString()
            var age = view.findViewById<EditText>(R.id.textAgeProf).getText().toString()
            var genre = view.findViewById<EditText>(R.id.textGenreProf).getText().toString()
            var cipa = view.findViewById<EditText>(R.id.textUserCipaProf).getText().toString()
            var info= view.findViewById<EditText>(R.id.textUserInfoProf).getText().toString()

            dbRegister.collection("users").document(email)
                .get()
                .addOnSuccessListener { documento ->
                    if (documento != null) {
                       var user_elder = documento.getString("user_elder")
                        if (user_elder != null){
                            if (name != ""){
                                dbRegister.collection("users").document(user_elder).update(
                                    "name", name)
                            }
                            if (surname != ""){
                                dbRegister.collection("users").document(user_elder).update(
                                    "surname", surname)
                            }
                            if (age!= ""){
                                dbRegister.collection("users").document(user_elder).update(
                                    "age", age)
                            }
                            if (genre!= ""){
                                dbRegister.collection("users").document(user_elder).update(
                                    "genre", genre)
                            }
                            if (cipa!= ""){
                                dbRegister.collection("users").document(user_elder).update(
                                    "cipa_number", cipa)
                            }
                            if (info!= ""){
                                dbRegister.collection("users").document(user_elder).update(
                                    "aditional_info", info)
                            }
                        }
                    }
                }

            saveEdit()
        }

        return view
    }

    private fun saveEdit() {
        val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, ProfileUserFromFragment())
        fragmentTransaction.commit()
    }

}