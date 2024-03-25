package com.example.remindmeinfo.ui.profile


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ProfileAdminFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    val usuario = FirebaseAuth.getInstance().currentUser
    val uem = usuario?.email

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
        lateinit var textViewElder: TextView



        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        textViewName = binding.textNameAdmin
        textViewSurname = binding.textSurnameAdmin
        textViewGenre = binding.textGenreAdmin
        textViewAge = binding.textAgeAdmin
        textViewAddInfo = binding.textInfoAdmin
        textViewEmail = binding.textEmailAdmin
        textViewElder = binding.textUserElderAdmin

        if (uem != null) {
            dbReference.collection("users").document(uem)
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

                        val email = documento.getString("user")
                        textViewEmail.text = email

                        val user_mayor = documento.getString("user_elder")
                        textViewElder.text = user_mayor

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

        val myButton: Button = view.findViewById(R.id.buttonEditProfile)
        myButton.setOnClickListener {
            // Aquí manejas el evento clic del botón
            editProfile()
        }
    }

    fun editProfile(){
        val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, EditProfileFragment())
        fragmentTransaction.commit()
    }

}