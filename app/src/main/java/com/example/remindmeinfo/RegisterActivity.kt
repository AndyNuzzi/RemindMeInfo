package com.example.remindmeinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    val currentUser = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun saveRegister(view: View){
        var email = currentUser?.email.toString()
        var dbRegister = FirebaseFirestore.getInstance()
        dbRegister.collection("users").document(email).update(
            "bool_admin", false)
        dbRegister.collection("users").document(email).update(
            "name", "")
        dbRegister.collection("users").document(email).update(
            "first_surname", "")
        dbRegister.collection("users").document(email).update(
            "second_surname", "")
        dbRegister.collection("users").document(email).update(
            "age", 0)
        dbRegister.collection("users").document(email).update(
            "genre", "")
        dbRegister.collection("users").document(email).update(
            "aditional_info", "")
        dbRegister.collection("users").document(email).update(
            "family", "")

        goNext()
    }

    fun goNext(){
        val intent = Intent(this, MainActivityAdmin::class.java)
        startActivity(intent)
    }
}